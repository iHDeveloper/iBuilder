package me.ihdeveloper.ibuilder;

import me.ihdeveloper.ibuilder.util.Console;

public class Main {

	public static void main(String[] args) {
		Console console = new Console(System.out);
		IBuilder.setConsole(console);
		console.log("This is a log message from console");
		console.warn("This is a warn message from console");
		console.err("This is a error message from console");
		try {
			console.loading("Clone Bukkit");
			Thread.sleep(1500);
			console.done();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
