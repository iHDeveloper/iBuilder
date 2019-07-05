package me.ihdeveloper.ibuilder.category;

import me.ihdeveloper.ibuilder.Category;
import me.ihdeveloper.ibuilder.task.SetupToolTask;

public class SetupCategory extends Category {
	
	public SetupCategory() {
		super("Setting up the tools");
		addTask("Bukkit");
		addTask("CraftBukkit");
		addTask("Spigot");
		addTask("BuildData");
	}
	
	public void addTask(String name) {
		super.addTask(new SetupToolTask(name));
	}

}
