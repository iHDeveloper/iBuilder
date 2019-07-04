package me.ihdeveloper.ibuilder;

public class Config {
	
	private final String name;
	private final String url;
	
	public Config(String name, String reposUrl) {
		this.name = name;
		this.url = reposUrl;
	}
	
	public String getName() {
		return name;
	}
	
	public String getUrl() {
		return url;
	}
	
}
