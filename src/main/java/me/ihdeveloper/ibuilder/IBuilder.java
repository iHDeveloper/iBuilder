package me.ihdeveloper.ibuilder;

import java.io.File;

import me.ihdeveloper.ibuilder.util.Console;

public final class IBuilder {
	
	private static Console console;
	private static File root;
	
	public static void setConsole(Console console) {
		IBuilder.console = console;
	}
	
	public static void setRoot(File root) {
		IBuilder.root = root;
	}
	
	public static Console getConsole() {
		return console;
	}
	
	public static File getRoot() {
		return root;
	}

}
