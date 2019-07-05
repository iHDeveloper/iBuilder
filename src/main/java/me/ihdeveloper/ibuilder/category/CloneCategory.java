package me.ihdeveloper.ibuilder.category;

import me.ihdeveloper.ibuilder.Category;
import me.ihdeveloper.ibuilder.IBuilder;
import me.ihdeveloper.ibuilder.task.CloneTask;
import me.ihdeveloper.ibuilder.util.Links;

public class CloneCategory extends Category {

	public CloneCategory() {
		super("Cloning Tools");
		addTask("Bukkit", Links.BUKKIT_URL);
		addTask("CraftBukkit", Links.CRAFTBUKKIT_URL);
		addTask("Spigot", Links.SPIGOT_URL);
		addTask("Build Data", Links.BUILD_DATA_URL);
	}
	
	public void addTask(String name, String url) {
		if (IBuilder.getRoot(name).exists())
			return;
		super.addTask(new CloneTask(name, url, name));
	}
	
}
