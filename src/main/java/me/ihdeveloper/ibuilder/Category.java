package me.ihdeveloper.ibuilder;

import java.util.ArrayList;

import me.ihdeveloper.ibuilder.util.Console;

public class Category {
	
	private static final String TASK_PREFIX = "|-> ";
	
	private final String name;
	private ArrayList<Task> tasks;
	
	public Category(String name) {
		this.name = name;
		this.tasks = new ArrayList<Task>();
	}
	
	public boolean start() {
		Console console = IBuilder.getConsole();
		console.log("-> " + getName());
		boolean state = true;
		for (Task task : tasks) {
			// TODO loading when the debug mode is off
			console.log(TASK_PREFIX + task.getName());
			boolean failed = !task.run();
//			console.done();
			if (failed) {
				console.err(TASK_PREFIX + task.getMessage());
				break;
			}
		}
		return state;
	}
	
	public void addTask(Task task) {
		this.tasks.add(task);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
