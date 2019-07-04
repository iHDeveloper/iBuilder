package me.ihdeveloper.ibuilder;

public abstract class Task {
	
	private final String name;
	private Category category;
	private String message = null;
	
	public Task(String name) {
		this.name = name;
	}
	
	public void setCategory(Category category) {
		this.category =  category;
	}
	
	public void setMessage(String message, Object... args) {
		this.message = String.format(message, args);
	}
	
	public String getName() {
		return this.name;
	}
	
	public Category getCategory() {
		return this.category;
	}
	
	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public abstract boolean run();
}
