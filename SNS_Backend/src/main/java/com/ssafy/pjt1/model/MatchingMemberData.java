package com.ssafy.pjt1.model;

public class MatchingMemberData {
	private int userid;
	private String nickname;
	private int preferProject;
	private byte[] profile;
	
	public MatchingMemberData(int userid, String nickname, int preferProject, byte[] profile) {
		this.userid = userid;
		this.nickname = nickname;
		this.preferProject = preferProject;
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

	public int getPreferProject() {
		return preferProject;
	}

	public void setPreferProject(int preferProject) {
		this.preferProject = preferProject;
	}

	public byte[] getProfile() {
		return profile;
	}

	public void setProfile(byte[] profile) {
		this.profile = profile;
	}
}
