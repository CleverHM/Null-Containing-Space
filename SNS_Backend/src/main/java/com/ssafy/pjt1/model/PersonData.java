package com.ssafy.pjt1.model;

public class PersonData {
	private int id;
	private byte[] file;
	private String nickname;
	private int followFlag;

	public PersonData(int id, byte[] file, String nickname, int followFlag) {
		this.id = id;
		this.file = file;
		this.nickname = nickname;
		this.followFlag = followFlag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
