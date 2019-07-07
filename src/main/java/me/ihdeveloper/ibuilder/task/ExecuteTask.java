package me.ihdeveloper.ibuilder.task;

import java.io.File;

import me.ihdeveloper.ibuilder.Task;
import me.ihdeveloper.ibuilder.type.ExecuteType;
import me.ihdeveloper.ibuilder.util.Worker;

public class ExecuteTask extends Task {

	private ExecuteType type;
	private File directory;
	private String[] command;
	
	public ExecuteTask(String name, ExecuteType type, File directory, String... command) {
		super(name);
		this.type = type;
		this.directory = directory;
		this.command = command;
	}
	
	@Override
	public boolean run() {
		try {
			if (type == ExecuteType.MAVEN)
				Worker.maven(directory, command);
			else if (type == ExecuteType.GIT)
				Worker.git(directory, command);
			else if (type == ExecuteType.CMD)
				Worker.cmd(directory, command);
			else
				Worker.create(directory, command);
		} catch (Exception e) {
			getConsole().debugf("Failed to execute: (%s/%s) %s", getName(), type, e.getMessage());
			setMessage("Failed to execute: (%s/%s) %s", getName(), type, e.getMessage());
			return false;
		}
		return true;
	}
	
}
