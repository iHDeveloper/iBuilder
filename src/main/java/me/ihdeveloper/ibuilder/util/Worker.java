package me.ihdeveloper.ibuilder.util;

import java.io.File;
import java.io.IOException;

import com.google.common.base.Preconditions;
import com.google.common.collect.ObjectArrays;

import me.ihdeveloper.ibuilder.IBuilder;

public class Worker {
	
	public static int cmd(File directory, String... command) throws IllegalArgumentException, IOException, InterruptedException {
		final String[] cmd = {
			"cmd.exe", "/C" 
		};
		return create(directory, ObjectArrays.concat(cmd, command, String.class));
	}
	
	public static int git(File directory, String... command) throws IllegalArgumentException, IOException, InterruptedException {
		String git = IBuilder.isWindows() ? IBuilder.getGit().getAbsolutePath() + "/bin/git.exe" : "git";
		return create(directory, ObjectArrays.concat(git, command));
	}
	
	public static int maven(File directory, String... command) throws IllegalArgumentException, IOException, InterruptedException {
		return cmd(directory, ObjectArrays.concat("mvn", command));
	}
	
	public static int create(File directory, String... command) throws IllegalArgumentException, IOException, InterruptedException {
		Worker worker = new Worker(directory, command);
		return worker.start();
	}
	
	private final File directory;
	private final String[] command;
	
	public Worker(File directory, String[] command) throws IllegalArgumentException {
		Preconditions.checkArgument(directory != null, "No working directory to start the command from");
		Preconditions.checkArgument(command != null && command.length > 0, "No command to start!");
		this.directory = directory;
		this.command = command;
	}
	
	public int start() throws IOException, InterruptedException {
		ProcessBuilder builder = new ProcessBuilder(getCommand());
		builder.directory(getDirectory());
		String pathKey = null;
		String path = null;
		for (String key : builder.environment().keySet()) {
			if (key.equalsIgnoreCase("path")) {
				pathKey = key;
				path = builder.environment().get(key);
			}
		}
		if (path != null) {
			if (IBuilder.getMaven() != null)
				path += ";" + IBuilder.getMaven().getAbsolutePath() + "/bin";
			builder.environment().put(pathKey, path);
		}
		final Process process = builder.start();
		final int exitCode = process.waitFor();
		return exitCode;
	}
	
	public File getDirectory() {
		return this.directory;
	}
	
	public String[] getCommand() {
		return command;
	}

}
