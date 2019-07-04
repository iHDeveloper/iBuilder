package me.ihdeveloper.ibuilder;

public class Config {
	
	private final String name;
	private final String reposUrl;
	
	public Config(String name, String reposUrl) {
		this.name = name;
		this.reposUrl = reposUrl;
	}
	
	public String getName() {
		return name;
	}
	
	public String getReposUrl() {
		return reposUrl;
	}
	
}
