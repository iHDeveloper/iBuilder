package me.ihdeveloper.ibuilder.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import com.google.common.base.Predicate;
import com.google.common.io.ByteStreams;

import me.ihdeveloper.ibuilder.Task;

public class UnzipTask extends Task {
	
	private final File source;
	private final File target;
	private final Predicate<String> filter;
	
	public UnzipTask(String name, File source, File target) {
		this(name, source, target, null);
	}
	
	public UnzipTask(String name, File source, File target, Predicate<String> filter) {
		super("Unzipping " + name);
		this.source = source;
		this.target = target;
		this.filter = filter;
	}
	
	@Override
	public boolean run() {
		target.mkdir();
		try {
			ZipFile zipFile = new ZipFile(source);
			for (Enumeration<? extends ZipEntry> entries = zipFile.entries(); entries.hasMoreElements();) {
				ZipEntry entry = entries.nextElement();
				if (filter != null && !filter.apply(entry.getName())) {
					continue;
				}
				File outputFile = new File(target, entry.getName());
				if (entry.isDirectory()) {
					outputFile.mkdirs();
					continue;
				}
				if (outputFile.getParentFile() != null) {
					outputFile.getParentFile().mkdirs();
				}
				getConsole().debug("Unzipping " + entry.getName() + "...");
				InputStream in = zipFile.getInputStream(entry);
				OutputStream out = new FileOutputStream(outputFile);
				try {
					ByteStreams.copy(in, out);
				} finally {
					in.close();
					out.close();
				}
			}
			zipFile.close();
		} catch (ZipException e) {
			setMessage("Failed to read the zip file format");
			return false;
		} catch (IOException e) {
			setMessage("I/O Error");
			return false;
		}
		return true;
	}
	
}
