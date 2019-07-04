package me.ihdeveloper.ibuilder.util;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {
	
	private final PrintStream out;
	
	public Console(PrintStream out) {
		this.out = out;
	}
	
	public PrintStream getOutput() {
		return out;
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
	
	private void println(String state, String message) {
		final String output = String.format("[%s] [%s]: %s", getDate(), state.toUpperCase(), message);
		this.out.println(output);
	}
	
	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
}
