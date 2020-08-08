package com.ssafy.pjt1.model;

public class MatchingData {
	private int userid;
	private String nickname;
	private byte[] profile;
	
	public MatchingData(int userid, String nickname, byte[] profile) {
		this.userid = userid;
		this.nickname = nickname;
		this.profile = profile;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public byte[] getProfile() {
		return profile;
	}

	public void setProfile(byte[] profile) {
		this.profile = profile;
	} 
}
