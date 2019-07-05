package me.ihdeveloper.ibuilder.task;

import java.io.File;

import me.ihdeveloper.ibuilder.IBuilder;
import me.ihdeveloper.ibuilder.Task;
import me.ihdeveloper.ibuilder.category.RequiresCategory;

public class SetupMavenTask extends Task {

	public SetupMavenTask() {
		super("Setting up Maven");
	}
	
	@Override
	public boolean run() {
		File zipFile = IBuilder.getRoot(RequiresCategory.MAVEN_VERSION + "-bin.zip");
		if (zipFile.exists())
			zipFile.delete();
		String m2Home = System.getenv(RequiresCategory.MAVEN_ENV);
		File mavenFolder;
		if (m2Home == null || !(mavenFolder = new File(m2Home)).exists()) {
			mavenFolder = new File(RequiresCategory.MAVEN_VERSION);
		}
		if (!mavenFolder.exists()) {
			setMessage("Failed to find the maven folder to setup");
			return false;
		}
		IBuilder.setMaven(mavenFolder);
		return true;
	}
	
}
