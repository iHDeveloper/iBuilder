package me.ihdeveloper.ibuilder.util;

public class BuildInfoReference {
	
	private String Bukkit;
	private String CraftBukkit;
	private String Spigot;
	private String BuildData;
	
	public BuildInfoReference(String Bukkit, String CraftBukkit, String Spigot, String BuildData) {
		this.Bukkit = Bukkit;
		this.CraftBukkit = CraftBukkit;
		this.Spigot = Spigot;
		this.BuildData = BuildData;
	}
	
	public void setBukkit(String Bukkit) {
		this.Bukkit = Bukkit;
	}
	
	public void setCraftbukkit(String CraftBukkit) {
		this.CraftBukkit = CraftBukkit;
	}
	
	public void setSpigot(String Spigot) {
		this.Spigot = Spigot;
	}
	
	public void setBuildData(String BuildData) {
		this.BuildData = BuildData;
	}
	
	public String getBukkit() {
		return Bukkit;
	}
	
	public String getCraftbukkit() {
		return CraftBukkit;
	}
	
	public String getSpigot() {
		return Spigot;
	}
	
	public String getBuildData() {
		return BuildData;
	}
	
}
