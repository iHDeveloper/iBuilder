package me.ihdeveloper.ibuilder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import me.ihdeveloper.ibuilder.util.Console;

public class EventManager {

	private List<Listener> listeners;
	
	public EventManager() {
		this.listeners = new ArrayList<Listener>();
	}
	
	public void addListener(Listener listener) {
		this.listeners.add(listener);
	}
	
	public void broadcast(Event event) {
		try {
			for (Listener listener : listeners) {
				Class<?> listenerClass = listener.getClass();
				for (Method method : listenerClass.getMethods()) {
					if (method.getAnnotationsByType(EventHandler.class).length != 1) return;
					method.invoke(listener, event);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Console console = IBuilder.getConsole();
			console.errf("Failed to broadcast event: %s [ %s ]", event.getName(), e.getMessage());
		}
	}
	
}
