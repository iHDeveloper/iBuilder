package me.ihdeveloper.ibuilder.util;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {
	
	private final PrintStream out;
	private long start;
	private String lastMessage;
	
	public Console(PrintStream out) {
		this.out = out;
	}
	
	public PrintStream getOutput() {
		return out;
	}
	
	public void logf(String format, Object... args) {
		this.log(String.format(format, args));
	}
	
	public void log(String message) {
		this.println("log", message);
	}
	
	public void warn(String message) {
		this.println("warn", message);
	}
	
	public void err(String message) {
		this.println("err", message);
	}
	
	public void debug(String message) {
		this.println("debug", message);
	}
	
	public void loading(String message) {
		this.print("log", message + " | Loading...\r"); 
		this.start = System.currentTimeMillis();
		this.lastMessage = message;
	}
	
	public void done() {
		long duration = System.currentTimeMillis() - start;
		String state = String.format(" | Done! (%sms)", duration);
		this.println("log", lastMessage + state);
		this.lastMessage = null;
		this.start = -1;
	}
	
	private void println(String state, String message) {
		this.print(state, message + "\n");
	}
	
	private void print(String state, String message) {
		final String output = String.format("[%s] [%s]: %s", getDate(), state.toUpperCase(), message);
		this.out.print(output);
	}
	
	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
}
