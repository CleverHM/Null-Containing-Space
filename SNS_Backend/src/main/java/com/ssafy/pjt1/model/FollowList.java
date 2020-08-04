package com.ssafy.pjt1.model;

public class FollowList {
	private byte[] file;
	private String nickname;
	
	public FollowList(byte[] file, String nickname) {
		this.file = file;
		this.nickname = nickname;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
