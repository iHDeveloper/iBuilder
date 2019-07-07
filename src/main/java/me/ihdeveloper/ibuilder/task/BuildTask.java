package me.ihdeveloper.ibuilder.task;

import java.io.File;

import me.ihdeveloper.ibuilder.Task;
import me.ihdeveloper.ibuilder.util.Worker;

public class BuildTask extends Task {
	
	private File target;
	
	public BuildTask(String name, File target) {
		super("Building " + name);
		this.target = target;
	}
	
	@Override
	public boolean run() {
		try {
			Worker.maven(target, "clean", "install");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
