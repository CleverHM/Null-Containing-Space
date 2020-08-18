package com.ssafy.pjt1.model;

public class HashAllData {
	String hash;
	boolean flag;

	public HashAllData(String hash, boolean flag) {
		this.hash = hash;
		this.flag = flag;
	}
	public String gethash() {
		return hash;
	}
	public void sethash(String hash) {
		this.hash = hash;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
