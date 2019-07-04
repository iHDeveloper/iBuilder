package me.ihdeveloper.ibuilder.util;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public enum HashFormat {
	MD5(0), SHA256(1), SHA512(2);
	
	private final int type;
	
	private HashFormat(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
	
	public HashFunction getHashing() {
		if (type == 0) return Hashing.md5();
		if (type == 1) return Hashing.sha256();
		return Hashing.sha512();
	}
	
}
