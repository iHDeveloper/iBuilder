package me.ihdeveloper.ibuilder;

import me.ihdeveloper.ibuilder.util.Console;

public final class IBuilder {
	
	private static Console console;
	
	public static void setConsole(Console console) {
		IBuilder.console = console;
	}
	
	public static Console getConsole() {
		return console;
	}

}
