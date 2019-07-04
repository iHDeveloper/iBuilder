package me.ihdeveloper.ibuilder;

import me.ihdeveloper.ibuilder.util.Console;

public class Category {
	
	private final String name;
	private Task[] tasks;
	
	public Category(String name, Task[] tasks) {
		this.name = name;
		setTasks(tasks);
	}
	
	public boolean start() {
		Console console = IBuilder.getConsole();
		console.log("-> " + getName());
		console.addPrefix();
		boolean state = true;
		for (Task task : tasks) {
			if (!task.run()) {
				console.err(task.getMessage());
				state = false;
				break;
			}
		}
		console.removePrefix();
		return state;
	}
	
	public void setTasks(Task[] tasks) {
		this.tasks = tasks;
		for (Task task : tasks) { 
			task.setCategory(this);
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public Task[] getTasks() {
		return tasks;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
