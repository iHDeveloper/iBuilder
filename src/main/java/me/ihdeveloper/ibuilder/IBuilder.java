package me.ihdeveloper.ibuilder;

import java.io.File;

import me.ihdeveloper.ibuilder.util.Console;

public final class IBuilder {
	
	private static Console console;
	private static File root;
	private static File git;
	
	public static void setConsole(Console console) {
		IBuilder.console = console;
	}
	
	public static void setRoot(File root) {
		IBuilder.root = root;
	}
	
	public static void setGit(File git) {
		IBuilder.git = git;
	}
	
	public static boolean isWindows() {
		return System.getProperty("os.name").startsWith("Windows") == true;
	}
	
	public static Console getConsole() {
		return console;
	}
	
	public static File getRoot() {
		return root;
	}
	
	public static File getGit() {
		return git;
	}
	
	public static File getRoot(String name) {
		return new File(root + "/" + name);
	}

}
