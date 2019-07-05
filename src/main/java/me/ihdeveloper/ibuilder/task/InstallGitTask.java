package me.ihdeveloper.ibuilder.task;

import java.io.File;

import me.ihdeveloper.ibuilder.IBuilder;
import me.ihdeveloper.ibuilder.Task;
import me.ihdeveloper.ibuilder.util.Worker;

public class InstallGitTask extends Task {

	public static final String GIT_FOLDER = "PortableGit";
	public static final String GIT_INTSALL_FILE = "git-installer.exe";
	
	public InstallGitTask() {
		super("Installing Git");
	}
	
	@Override
	public boolean run() {
		File installer = IBuilder.getRoot(GIT_INTSALL_FILE);
		if (!installer.exists()) {
			return true;
		}
		setMessage("Failed to install git");
		try {
			Worker.create(IBuilder.getRoot(), installer.getAbsolutePath(), "-y", "-gm2", "-nr");
			installer.delete();
		} catch (Exception e) {
			getConsole().debug(e.getMessage());
			return false;
		}
		return true;
	}
	
}
