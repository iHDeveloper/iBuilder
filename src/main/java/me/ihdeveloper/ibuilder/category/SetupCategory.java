package me.ihdeveloper.ibuilder.category;

import me.ihdeveloper.ibuilder.Category;
import me.ihdeveloper.ibuilder.task.FetchVersionInfoTask;
import me.ihdeveloper.ibuilder.task.SetupToolTask;
import me.ihdeveloper.ibuilder.util.BuildInfoReference;

public class SetupCategory extends Category {
	
	public SetupCategory(BuildInfoReference ref) {
		super("Setting up the tools");
		addTask("Bukkit", ref.getBukkit());
		addTask("CraftBukkit", ref.getCraftbukkit());
		addTask("Spigot", ref.getSpigot());
		addTask("BuildData", ref.getBuildData());
		super.addTask(new FetchVersionInfoTask());
	}
	
	public void addTask(String name, String ref) {
		super.addTask(new SetupToolTask(name, ref));
	}

}
