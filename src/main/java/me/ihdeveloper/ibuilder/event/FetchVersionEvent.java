package me.ihdeveloper.ibuilder.event;

import me.ihdeveloper.ibuilder.Event;
import me.ihdeveloper.ibuilder.util.VersionInfo;

public class FetchVersionEvent extends Event {

	private VersionInfo versionInfo;
	
	public FetchVersionEvent(VersionInfo versionInfo) {
		super("Fetch Version Info");
		this.versionInfo = versionInfo;
	}
	
	public VersionInfo getVersionInfo() {
		return versionInfo;
	}
	
}
