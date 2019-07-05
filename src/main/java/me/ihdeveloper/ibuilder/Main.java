package me.ihdeveloper.ibuilder;

import java.io.File;

import me.ihdeveloper.ibuilder.category.CloneCategory;
import me.ihdeveloper.ibuilder.category.RequiresCategory;
import me.ihdeveloper.ibuilder.util.Console;

public class Main {

	public static void main(String[] args) {
		Console console = new Console(System.out);
		IBuilder.setConsole(console);
		IBuilder.setRoot(new File("."));
		console.log("OS Name: " + System.getProperty("os.name"));
		console.log("Building...");
		System.out.println();
		Category[] categories = {
				new RequiresCategory(),
				new CloneCategory()
		};
		for (Category category : categories) {
			boolean failed = !category.start();
			if (failed)
				break;
		}
	}
	
}
