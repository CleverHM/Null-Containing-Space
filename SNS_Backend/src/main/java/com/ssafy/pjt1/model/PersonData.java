package com.ssafy.pjt1.model;

public class PersonData {
	private byte[] file;
	private String nickname;
	private int followFlag;
	
	public PersonData(byte[] file, String nickname, int followFlag) {
		this.file = file;
		this.nickname = nickname;
		this.followFlag = followFlag;
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
	public int getFollowFlag() {
		return followFlag;
	}
	public void setFollowFlag(int followFlag) {
		this.followFlag = followFlag;
	}
}
