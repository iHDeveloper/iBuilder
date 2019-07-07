package me.ihdeveloper.ibuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.eclipse.jgit.api.Git;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;

import me.ihdeveloper.ibuilder.util.BuildInfo;
import me.ihdeveloper.ibuilder.util.Console;
import me.ihdeveloper.ibuilder.util.Links;

public final class IBuilder {
	
	private static Console console;
	private static File root;
	private static File git;
	private static File maven;
	private static Git bukkit;
	private static Git craftBukkit;
	private static Git spigot;
	private static Git buildData;
	
	public static BuildInfo fetchBuildInfo(String version) throws IOException {
		final String url = String.format("%s%s%s", Links.SPIGOTMC_VERSIONS, version, ".json");
		URLConnection connection = new URL(url).openConnection();
		connection.setConnectTimeout(10 * 1000);
		connection.setReadTimeout(10 * 1000);
		InputStreamReader in = null;
		try {
			in = new InputStreamReader(connection.getInputStream());
			String json = CharStreams.toString(in);
			return new Gson().fromJson(json, BuildInfo.class);
		} finally {
			if (in != null)
				in.close();
		}
	}
	
	public static void setConsole(Console console) {
		IBuilder.console = console;
	}
	
	public static void setRoot(File root) {
		IBuilder.root = root;
	}
	
	public static void setGit(File git) {
		IBuilder.git = git;
	}
	
	public static void setMaven(File maven) {
		IBuilder.maven = maven;
	}
	
	public static void setBukkit(Git bukkit) {
		IBuilder.bukkit = bukkit;
	}
	
	public static void setCraftBukkit(Git craftBukkit) {
		IBuilder.craftBukkit = craftBukkit;
	}
	
	public static void setSpigot(Git spigot) {
		IBuilder.spigot = spigot;
	}
	
	public static void setBuildData(Git buildData) {
		IBuilder.buildData = buildData;
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
	
	public static File getMaven() {
		return maven;
	}
	
	public static Git getBukkit() {
		return bukkit;
	}
	
	public static Git getCraftBukkit() {
		return craftBukkit;
	}
	
	public static Git getSpigot() {
		return spigot;
	}
	
	public static Git getBuildData() {
		return buildData;
	}
	
	public static File getRoot(String name) {
		return new File(root + "/" + name);
	}

}
