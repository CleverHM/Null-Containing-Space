package com.ssafy.pjt1.model;

public class TeamPersonData {
	private String nickname;
	private byte[] file;
	
	public TeamPersonData(String nickname, byte[] file) {
		super();
		this.nickname = nickname;
		this.file = file;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
}
