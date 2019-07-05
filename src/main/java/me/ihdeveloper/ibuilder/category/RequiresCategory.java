package me.ihdeveloper.ibuilder.category;

import java.io.File;

import me.ihdeveloper.ibuilder.Category;
import me.ihdeveloper.ibuilder.IBuilder;
import me.ihdeveloper.ibuilder.task.DownloadTask;
import me.ihdeveloper.ibuilder.task.InstallGitTask;
import me.ihdeveloper.ibuilder.util.HashFormat;
import me.ihdeveloper.ibuilder.util.Hashes;

public class RequiresCategory extends Category {

	private static final String GIT_URL = "https://static.spigotmc.org/git/";
	
	public RequiresCategory() {
		super("Requirements");
		if (IBuilder.isWindows())
			installGit();
	}
	
	private void installGit() {
		String proofHash;
		if (System.getProperty("os.arch").endsWith("64"))
			proofHash = Hashes.GIT_64;
		else
			proofHash = Hashes.GIT_32;
		final String gitVersion = "PortableGit-2.15.0-" + ( System.getProperty( "os.arch" ).endsWith( "64" ) ? "64" : "32" ) + "-bit.7z.exe";
		File gitFolder = IBuilder.getRoot("Git");
		if (gitFolder.exists()) return;
		addTask(new DownloadTask("Git", GIT_URL + gitVersion, InstallGitTask.GIT_INTSALL_FILE, HashFormat.SHA256, proofHash));
		addTask(new InstallGitTask());
	}
	
}
