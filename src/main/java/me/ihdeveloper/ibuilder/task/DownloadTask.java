package me.ihdeveloper.ibuilder.task;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.common.io.Files;
import com.google.common.io.Resources;

import me.ihdeveloper.ibuilder.Config;
import me.ihdeveloper.ibuilder.IBuilder;
import me.ihdeveloper.ibuilder.Task;
import me.ihdeveloper.ibuilder.util.HashFormat;

public class DownloadTask extends Task {
	
	private final String name;
	private final String url;
	private final String target;
	private final HashFormat hashFormat;
	private final String proofHash;
	
	public DownloadTask(Config config, String target, String proofHash) {
		this(config, target, HashFormat.MD5, proofHash);
	}
	
	public DownloadTask(Config config, String target, HashFormat hashFormat, String proofHash) {
		this(config.getName(), config.getUrl(), target, hashFormat, proofHash);
	}
	
	public DownloadTask(String name, String url, String target, HashFormat hashFormat, String proofHash) {
		super("Downloading " + name + " [ " + url + " ]");
		this.name = name;
		this.url = url;
		this.target = target;
		this.hashFormat = hashFormat;
		this.proofHash = proofHash;
	}
	
	@Override
	public boolean run() {
		File targetFile = IBuilder.getRoot(target);
		if (targetFile.exists()) {
			return true;
		}
		try {
			byte[] data = Resources.toByteArray(new URL(this.url));
			String hash = hashFormat.getHashing().hashBytes(data).toString();
			if (!hash.equalsIgnoreCase(proofHash)) {
				setMessage("hash of the file is not proofed!");
				return false;
			}
			Files.write(data, targetFile);
			return true;
		} catch (MalformedURLException ex) {
			setMessage("Wrong url");
		} catch (IOException ex) {
			setMessage("I/O Error");
		}
		return false;
	}
	
	@Override
	public void setMessage(String message, Object... args) {
		super.setMessage("Failed to download: %s ( %s )", name, String.format(message, args));
	}
	
}
