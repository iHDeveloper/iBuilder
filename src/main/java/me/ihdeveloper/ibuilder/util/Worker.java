package me.ihdeveloper.ibuilder.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Preconditions;

import me.ihdeveloper.ibuilder.IBuilder;

public class Worker {
	
	public static int git(File directory, String... command) throws IllegalArgumentException, IOException, InterruptedException {
		List<String> args = new ArrayList<String>();
		if (IBuilder.isWindows()) args.add(IBuilder.getGit().getAbsolutePath() + "/bin/git.exe");
		else args.add("git");
		args.addAll(Arrays.asList(command));
		return create(directory, args.toArray(new String[0]));
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
		// TODO edit the environment
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
