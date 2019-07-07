package me.ihdeveloper.ibuilder;

import java.io.File;
import java.io.IOException;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import me.ihdeveloper.ibuilder.category.CloneCategory;
import me.ihdeveloper.ibuilder.category.RequiresCategory;
import me.ihdeveloper.ibuilder.category.SetupCategory;
import me.ihdeveloper.ibuilder.util.BuildInfo;
import me.ihdeveloper.ibuilder.util.Console;

public class Main {

	public static void main(String[] args) {
		OptionParser parser = new OptionParser();
		// TODO write description to the arguments
		OptionSpec<Void> help = parser.accepts("help");
		OptionSpec<String> version = parser.accepts("version").withRequiredArg().defaultsTo("latest");
		OptionSet options = parser.parse(args);
		
		if (options.has(help)) {
			try {
				parser.printHelpOn(System.out);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		Console console = new Console(System.out);
		IBuilder.setConsole(console);
		IBuilder.setRoot(new File("."));
		console.log("OS Name: " + System.getProperty("os.name"));
		
		String jenkinsVersion = options.valueOf(version);
		console.logf("Fetching version %s...", jenkinsVersion);
		BuildInfo buildInfo;
		try {
			buildInfo = IBuilder.fetchBuildInfo(jenkinsVersion);
		} catch (Exception e) {
			console.err("Failed to fetch the version!");
			System.exit(1);
			return;
		}
		IBuilder.setBuildInfo(buildInfo);
		
		
		console.log("Building...");
		System.out.println();
		Category[] categories = {
				new RequiresCategory(),
				new CloneCategory(),
				new SetupCategory(buildInfo.getReference())
		};
		for (Category category : categories) {
			boolean failed = !category.start();
			if (failed)
				break;
		}
	}
	
}
