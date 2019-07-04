package me.ihdeveloper.ibuilder;

public abstract class Task {
	
	private final String name;
	private Category category;
	
	public Task(String name) {
		this.name = name;
	}
	
	public void setCategory(Category category) {
		this.category =  category;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Category getCategory() {
		return this.category;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public abstract void run();
}
