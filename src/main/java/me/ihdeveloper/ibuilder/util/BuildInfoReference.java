package me.ihdeveloper.ibuilder.util;

public class BuildInfoReference {
	
	private String bukkit;
	private String craftbukkit;
	private String spigot;
	private String buildData;
	
	public BuildInfoReference(String bukkit, String craftbukkit, String spigot, String buildData) {
		this.bukkit = bukkit;
		this.craftbukkit = craftbukkit;
		this.spigot = spigot;
		this.buildData = buildData;
	}
	
	public void setBukkit(String bukkit) {
		this.bukkit = bukkit;
	}
	
	public void setCraftbukkit(String craftbukkit) {
		this.craftbukkit = craftbukkit;
	}
	
	public void setSpigot(String spigot) {
		this.spigot = spigot;
	}
	
	public void setBuildData(String buildData) {
		this.buildData = buildData;
	}
	
	public String getBukkit() {
		return bukkit;
	}
	
	public String getCraftbukkit() {
		return craftbukkit;
	}
	
	public String getSpigot() {
		return spigot;
	}
	
	public String getBuildData() {
		return buildData;
	}
	
}
