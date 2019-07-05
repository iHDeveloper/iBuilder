package me.ihdeveloper.ibuilder.category;

import java.io.File;

import me.ihdeveloper.ibuilder.Category;
import me.ihdeveloper.ibuilder.IBuilder;
import me.ihdeveloper.ibuilder.task.DownloadTask;
import me.ihdeveloper.ibuilder.task.InstallGitTask;
import me.ihdeveloper.ibuilder.task.SetupGitTask;
import me.ihdeveloper.ibuilder.task.SetupMavenTask;
import me.ihdeveloper.ibuilder.task.UnzipTask;
import me.ihdeveloper.ibuilder.util.HashFormat;
import me.ihdeveloper.ibuilder.util.Hashes;

public class RequiresCategory extends Category {

	public static final String MAVEN_VERSION = "apache-maven-3.6.0";
	public static final String MAVEN_ENV = "M2_HOME";
	private static final String SPIGOTMC_STATIC_URL = "https://static.spigotmc.org/";
	
	public RequiresCategory() {
		super("Requirements");
		if (IBuilder.isWindows())
			installGit();
		installMaven();
	}
	
	private void installGit() {
		String proofHash;
		if (System.getProperty("os.arch").endsWith("64"))
			proofHash = Hashes.GIT_64;
		else
			proofHash = Hashes.GIT_32;
		final String gitVersion = "PortableGit-2.15.0-" + ( System.getProperty( "os.arch" ).endsWith( "64" ) ? "64" : "32" ) + "-bit.7z.exe";
		File gitFolder = IBuilder.getRoot(InstallGitTask.GIT_FOLDER);
		if (!gitFolder.exists()) {
			addTask(new DownloadTask("Git", SPIGOTMC_STATIC_URL + "git/" + gitVersion, InstallGitTask.GIT_INTSALL_FILE, HashFormat.SHA256, proofHash));
			addTask(new InstallGitTask());
		}
		addTask(new SetupGitTask());
	}
	
	private void installMaven() {
		String m2Home = System.getenv(MAVEN_ENV);
		if (m2Home == null || !(new File(m2Home).exists())) {
			File mavenFolder = new File(MAVEN_VERSION);
			final String mavenFilename = MAVEN_VERSION + "-bin.zip";
			if (!mavenFolder.exists()) {
				addTask(new DownloadTask("Maven", SPIGOTMC_STATIC_URL + "maven/" + mavenFilename, mavenFilename, HashFormat.SHA512, Hashes.MAVEN));
				addTask(new UnzipTask("Unzip", IBuilder.getRoot(mavenFilename), IBuilder.getRoot()));
			}
		}
		addTask(new SetupMavenTask());
	}
	
}
