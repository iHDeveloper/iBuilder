package me.ihdeveloper.ibuilder.util;

import java.util.HashMap;
import java.util.Map;

public class JavaVersion {

	public static final JavaVersion JAVA_5 = new JavaVersion("Java 5", 49);
	public static final JavaVersion JAVA_6 = new JavaVersion("Java 6", 50);
	public static final JavaVersion JAVA_7 = new JavaVersion("Java 7", 51);
	public static final JavaVersion JAVA_8 = new JavaVersion("Java 8", 52);
	public static final JavaVersion JAVA_9 = new JavaVersion("Java 9", 53);
	public static final JavaVersion JAVA_10 = new JavaVersion("Java 10", 54);
	public static final JavaVersion JAVA_11 = new JavaVersion("Java 11", 55);
	public static final JavaVersion JAVA_12 = new JavaVersion("Java 12", 56);
	
	private static Map<Integer, JavaVersion> versionsById = new HashMap<Integer, JavaVersion>();
	
	public static JavaVersion getById(int number) {
		return versionsById.get(number);
	}
	
	public static JavaVersion getCurrentVersion() {
		return getById((int) Float.parseFloat(System.getProperty("java.class.version")));
	}
	
	private String name;
	private int number;
	private boolean unknown;
	
	public JavaVersion(String name, int number) {
		this(name, number, false);
	}
	
	public JavaVersion(String name, int number, boolean unknown) {
		this.name = name;
		this.number = number;
		this.unknown = unknown;
		versionsById.put(number, this);
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isUnknown() {
		return unknown;
	}
	
	public int getNumber() {
		return number;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
