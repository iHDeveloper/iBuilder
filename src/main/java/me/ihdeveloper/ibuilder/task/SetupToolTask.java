package me.ihdeveloper.ibuilder.task;

import java.io.File;
import java.lang.reflect.Method;

import org.eclipse.jgit.api.Git;

import me.ihdeveloper.ibuilder.IBuilder;
import me.ihdeveloper.ibuilder.Task;

public class SetupToolTask extends Task {

	private String name;
	
	public SetupToolTask(String name) {
		super("Setting up " + name);
		this.name = name;
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
			setMethod.invoke(null, git);
		} catch (Exception e) {
			getConsole().debug(e.getMessage());
			return false;
		}
		return true;
	}
	
}
