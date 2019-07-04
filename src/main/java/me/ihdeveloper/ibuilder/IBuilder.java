package me.ihdeveloper.ibuilder;

import java.io.File;

import me.ihdeveloper.ibuilder.util.Console;

public final class IBuilder {
	
	private static Console console;
	private static File git;
	private static File maven;
	
	public static void setConsole(Console console) {
		IBuilder.console = console;
	}
	
	public static Console getConsole() {
		return console;
	}

}
