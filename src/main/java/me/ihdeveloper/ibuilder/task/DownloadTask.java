package me.ihdeveloper.ibuilder.task;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.common.io.Resources;

import me.ihdeveloper.ibuilder.Config;
import me.ihdeveloper.ibuilder.IBuilder;
import me.ihdeveloper.ibuilder.Task;
import me.ihdeveloper.ibuilder.util.HashFormat;

public class DownloadTask extends Task {
	
	private final Config config;
	private final String target;
	private final HashFormat hashFormat;
	private final String proofHash;
	
	public DownloadTask(Config config, String target, String proofHash) {
		this(config, target, HashFormat.MD5, proofHash);
	}
	
	public DownloadTask(Config config, String target, HashFormat hashFormat, String proofHash) {
		super("Download " + config.getName());
		this.config = config;
		this.target = target;
		this.hashFormat = hashFormat;
		this.proofHash = proofHash;
	}
	
	@Override
	public boolean run() {
		File targetDir = new File(IBuilder.getRoot() + target);
		if (targetDir.exists()) {
			return true;
		}
		try {
			byte[] data = Resources.toByteArray(new URL(config.getUrl()));
			String hash = hashFormat.getHashing().hashBytes(data).toString();
			if (hash != proofHash) {
				setMessage("Hash of the file is not proofed!");
				return false;
			}
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
		super.setMessage("Failed to download: %s ( %s )", config.getName(), String.format(message, args));
	}
	
	public Config getConfig() {
		return config;
	}
	
}
