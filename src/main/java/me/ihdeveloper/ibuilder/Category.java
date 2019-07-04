package me.ihdeveloper.ibuilder;

public class Category {
	
	private final String name;
	private Task[] tasks;
	
	public Category(String name, Task[] tasks) {
		this.name = name;
		setTasks(tasks);
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
