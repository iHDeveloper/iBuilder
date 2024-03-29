package me.ihdeveloper.ibuilder.util;

public class BuildInfo {

	private String name;
	private String description;
	private int[] javaVersions;
	private BuildInfoReference refs;
	
	public BuildInfo(String name, String description, int[] javaVersions, BuildInfoReference refs) {
		this.name = name;
		this.description = description;
		this.javaVersions = javaVersions;
		this.refs = refs;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setJavaVersions(int[] javaVersions) {
		this.javaVersions = javaVersions;
	}
	
	public void setReference(BuildInfoReference refs) {
		this.refs = refs;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int[] getJavaVersions() {
		return javaVersions;
	}
	
	public BuildInfoReference getReference() {
		return refs;
	}
	
}
