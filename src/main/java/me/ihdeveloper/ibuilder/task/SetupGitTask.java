package me.ihdeveloper.ibuilder.task;

import java.io.File;

import me.ihdeveloper.ibuilder.IBuilder;
import me.ihdeveloper.ibuilder.Task;
import me.ihdeveloper.ibuilder.util.Worker;

public class SetupGitTask extends Task {

	public SetupGitTask() {
		super("Setting up Git");
	}
	
	@Override
	public boolean run() {
		File git = IBuilder.getRoot(InstallGitTask.GIT_FOLDER);
		if (!git.isDirectory() || !git.exists()) {
			setMessage("Failed to find the git folder");
			return false;
		}
		try {
			IBuilder.setGit(git);
			Worker.git(IBuilder.getRoot(), "--version");
			try {
				Worker.git(IBuilder.getRoot(), "config", "--global", "--includes", "user.name");
			} catch (Exception e) {
				Worker.git(IBuilder.getRoot(), "config", "--global", "user.name", "iBuilder");
			}
			try {
				Worker.git(IBuilder.getRoot(), "config", "--global", "--includes", "user.email");
			} catch (Exception e) {
				Worker.git(IBuilder.getRoot(), "config", "--global", "user.email", "ibuilder@ihdeveloper.github.com");
			}
		} catch (Exception ex) {
			getConsole().debug(ex.getMessage());
			return false;
		}
		return true;
	}
	
}
