package me.ihdeveloper.ibuilder.task;

import java.io.File;

import me.ihdeveloper.ibuilder.IBuilder;
import me.ihdeveloper.ibuilder.Task;
import me.ihdeveloper.ibuilder.util.Worker;

public class CloneTask extends Task {
	
	private String url;
	private String target;
	private File source;
	
	public CloneTask(String name, String url, String target) {
		this(name, url, target, IBuilder.getRoot());
	}
	
	public CloneTask(String name, String url, String target, File source) {
		super("Cloning " + name);
		this.url = url;
		this.target = target;
		this.source = source;
	}
	
	@Override
	public boolean run() {
		setMessage("Failed to clone");
		try {
			Worker.git(source, "clone", url, target);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
