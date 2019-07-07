package me.ihdeveloper.ibuilder.category;

import java.io.File;
import java.text.MessageFormat;

import me.ihdeveloper.ibuilder.Category;
import me.ihdeveloper.ibuilder.EventHandler;
import me.ihdeveloper.ibuilder.IBuilder;
import me.ihdeveloper.ibuilder.Listener;
import me.ihdeveloper.ibuilder.event.FetchVersionEvent;
import me.ihdeveloper.ibuilder.task.BuildTask;
import me.ihdeveloper.ibuilder.task.DownloadTask;
import me.ihdeveloper.ibuilder.task.ExecuteTask;
import me.ihdeveloper.ibuilder.type.ExecuteType;
import me.ihdeveloper.ibuilder.util.HashFormat;
import me.ihdeveloper.ibuilder.util.VersionInfo;

public class BuildBukkitCategory extends Category implements Listener {

	public BuildBukkitCategory() {
		super("Build Bukkit API & Craftbukkit");
		addTask(new BuildTask("Bukkit API", IBuilder.getRoot("Bukkit")));
		IBuilder.getEventManager().addListener(this);
	}

	@EventHandler
	public void onFetchVersion(FetchVersionEvent event) {
		VersionInfo versionInfo = event.getVersionInfo();
		final String vanillaJarName = "vanilla-" + versionInfo.getMinecraftVersion() + ".jar";
		final File vanillaJarFile = IBuilder.getTemp(vanillaJarName);
		if (!vanillaJarFile.exists()) {
			if (versionInfo.getServerUrl() != null) {
				addTask(new DownloadTask("Minecraft Server Vanilla", versionInfo.getServerUrl(), vanillaJarName,
						HashFormat.MD5, versionInfo.getMinecraftHash(), true));
			} else {
				final String vanillaDownloadUrl = String.format(
						"https://s3.amazonaws.com/Minecraft.Download/versions/%1$s/minecraft_server.%1$s.jar",
						versionInfo.getMinecraftVersion());
				addTask(new DownloadTask("Minecraft Server Vanilla", vanillaDownloadUrl, vanillaJarName, HashFormat.MD5,
						versionInfo.getMinecraftHash(), true));
			}
		}
		File finalMappedJar = IBuilder.getTemp("mapped-vanilla-" + versionInfo.getMinecraftVersion() + ".jar");
		if (!finalMappedJar.exists()) {
			if (versionInfo.getClassMapCommand() == null)
				versionInfo.setClassMapCommand("java -jar BuildData/bin/SpecialSource-2.jar map -i {0} -m {1} -o {2}");
			if (versionInfo.getMemberMapCommand() == null)
				versionInfo.setMemberMapCommand("java -jar BuildData/bin/SpecialSource-2.jar map -i {0} -m {1} -o {2}");
			if (versionInfo.getFinalMapCommand() == null)
				versionInfo.setFinalMapCommand("java -jar BuildData/bin/SpecialSource.jar --kill-lvt -i {0} --access-transformer {1} -m {2} -o {3}");
			if (versionInfo.getDecompileCommand() == null)
				versionInfo.setDecompileCommand("java -jar BuildData/bin/fernflower.jar -dgs=1 -hdc=0 -rbr=0 -asc=1 -udv=0 {0} {1}");
			File classMappedJar = IBuilder.getTemp("class-mapped-vanilla-" + versionInfo.getMinecraftVersion() + ".jar");
			File memberMappedJar = IBuilder.getTemp("member-mapped-vanilla-" + versionInfo.getMinecraftVersion() + ".jar");
			final String MAPPINGS_PATH = "BuildData/mappings/";
			String classMapCommand = MessageFormat.format(versionInfo.getClassMapCommand(), vanillaJarFile.getPath(), MAPPINGS_PATH + versionInfo.getClassMappings(), classMappedJar.getPath());
			String memberMapCommand = MessageFormat.format(versionInfo.getMemberMapCommand(), classMappedJar.getPath(), MAPPINGS_PATH + versionInfo.getMemberMappings(), memberMappedJar.getPath());
			String finalMapCommand = MessageFormat.format(versionInfo.getFinalMapCommand(), memberMappedJar.getPath(), MAPPINGS_PATH + versionInfo.getAccessTransforms(), MAPPINGS_PATH + versionInfo.getPackageMappings(), finalMappedJar.getPath());
			versionInfo.setClassMapCommand(classMapCommand);
			versionInfo.setMemberMapCommand(memberMapCommand);
			versionInfo.setFinalMapCommand(finalMapCommand);
			addTask(new ExecuteTask("Extracting the classes from the mapped jar", ExecuteType.CMD, IBuilder.getRoot(), versionInfo.getClassMapCommand().split(" ")));
			addTask(new ExecuteTask("Extracting the members from the mapped jar", ExecuteType.CMD, IBuilder.getRoot(), versionInfo.getMemberMapCommand().split(" ")));
			addTask(new ExecuteTask("Extracting the packages from the mapped jar", ExecuteType.CMD, IBuilder.getRoot(), versionInfo.getFinalMapCommand().split(" ")));
		}
		String[] buildCommand = {
				"install:install-file",
				"-Dfile=" + finalMappedJar,
				"-Dpackaging=jar",
				"-DgroupId=org.spigotmc",
				"-DartifactId=minecraft-server",
				"-Dversion=" + versionInfo.getMinecraftVersion() + "-SNAPSHOT"
		};
		addTask(new ExecuteTask("Build the final mapped jar", ExecuteType.MAVEN, IBuilder.getRoot(), buildCommand));
	}

}
