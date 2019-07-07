package me.ihdeveloper.ibuilder.task;

import java.io.File;
import java.lang.reflect.Method;

import org.eclipse.jgit.api.Git;

import me.ihdeveloper.ibuilder.IBuilder;
import me.ihdeveloper.ibuilder.Task;

public class SetupToolTask extends Task {

	private String name;
	private String ref;
	
	public SetupToolTask(String name, String ref) {
		super("Setting up " + name);
		this.name = name;
		this.ref = ref;
	}
	
	public boolean run() {
		File folder = IBuilder.getRoot(name);
		if (!folder.exists() || !folder.isDirectory()) {
			setMessage("Unable to find the folder to setup");
			return false;
		}
		setMessage("Unable to setup");
		try {
			Class<?> iBuilderClass = IBuilder.class;
			Method setMethod = iBuilderClass.getDeclaredMethod("set" + name, Git.class);
			Git git = Git.open(folder);
			git.checkout().setName("master").setForce(true).call();
			git.branchDelete().setBranchNames("ibuilder").setForce(true).call();
			git.branchCreate().setStartPoint(ref).setName("ibuilder").setForce(true).call();
			git.checkout().setName("ibuilder").setForce(true).call();
			getConsole().debugf("Created branch ibuilder in /%s from %s", name, ref);
			setMethod.invoke(null, git);
		} catch (Exception e) {
			getConsole().debug(e.getMessage());
			return false;
		}
		return true;
	}
	
}
