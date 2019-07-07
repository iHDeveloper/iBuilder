package me.ihdeveloper.ibuilder.util;

public class VersionInfo {
	
	private String minecraftVersion;
	private String accessTransforms;
	private String classMappings;
	private String memberMappings;
	private String packageMappings;
	private String minecraftHash;
	private String classMapCommand;
	private String memberMapCommand;
	private String finalMapCommand;
	private String decompileCommand;
	private String serverUrl;
	
	public VersionInfo(
			String minecraftVersion, String accessTransforms,
			String classMappings, String memberMappings,
			String packageMappings, String minecraftHash
			) {
		this.minecraftVersion = minecraftVersion;
		this.accessTransforms = accessTransforms;
		this.classMappings = classMappings;
		this.memberMappings = memberMappings;
		this.packageMappings = packageMappings;
		this.minecraftHash = minecraftHash;
	}
	
	public VersionInfo(
			String minecraftVersion, String accessTransforms,
			String classMappings, String memberMappings,
			String packageMappings, String minecraftHash,
			String classMapComamnd, String memberMapCommand,
			String finalMapCommand, String decompileCommand,
			String serverUrl
		) {
		this.minecraftVersion = minecraftVersion;
		this.accessTransforms = accessTransforms;
		this.classMappings = classMappings;
		this.memberMappings = memberMappings;
		this.packageMappings = packageMappings;
		this.minecraftHash = minecraftHash;
		this.classMapCommand = classMapComamnd;
		this.memberMapCommand = memberMapCommand;
		this.finalMapCommand = finalMapCommand;
		this.decompileCommand = decompileCommand;
		this.serverUrl = serverUrl;
	}
	
	public String getMinecraftVersion() {
		return minecraftVersion;
	}
	
	public String getAccessTransforms() {
		return accessTransforms;
	}
	
	public String getClassMappings() {
		return classMappings;
	}
	
	public String getMemberMappings() {
		return memberMappings;
	}
	
	public String getPackageMappings() {
		return packageMappings;
	}
	
	public String getMinecraftHash() {
		return minecraftHash;
	}
	
	public String getClassMapCommand() {
		return classMapCommand;
	}
	
	public String getMemberMapCommand() {
		return memberMapCommand;
	}
	
	public String getFinalMapCommand() {
		return finalMapCommand;
	}
	
	public String getDecompileCommand() {
		return decompileCommand;
	}
	
	public String getServerUrl() {
		return serverUrl;
	}

}
