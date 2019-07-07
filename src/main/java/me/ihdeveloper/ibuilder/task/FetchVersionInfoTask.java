package me.ihdeveloper.ibuilder.task;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;

import me.ihdeveloper.ibuilder.IBuilder;
import me.ihdeveloper.ibuilder.Task;
import me.ihdeveloper.ibuilder.util.VersionInfo;

public class FetchVersionInfoTask extends Task {

	public FetchVersionInfoTask() {
		super("Fetching version information");
	}
	
	@Override
	public boolean run() {
		setMessage("Failed to fetch the version information");
		try {
			 String json = Files.toString(IBuilder.getRoot("BuildData/info.json"), Charsets.UTF_8);
			 VersionInfo versionInfo = new Gson().fromJson(json, VersionInfo.class);
			 if (versionInfo == null) {
				 versionInfo = new VersionInfo( "1.8", "bukkit-1.8.at", "bukkit-1.8-cl.csrg", "bukkit-1.8-members.csrg", "package.srg", null);
			 }
			 IBuilder.setVersionInfo(versionInfo);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
