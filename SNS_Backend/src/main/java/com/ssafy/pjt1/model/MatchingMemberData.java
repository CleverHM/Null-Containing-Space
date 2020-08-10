package com.ssafy.pjt1.model;

public class MatchingMemberData {
	private int userid;
	private String nickname;
	private int preferProject;
	private int percent;
	private byte[] profile;

	public MatchingMemberData(int userid, String nickname, int preferProject, int percent, byte[] profile) {
		this.userid = userid;
		this.nickname = nickname;
		this.preferProject = preferProject;
		this.percent = percent;
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

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public byte[] getProfile() {
		return profile;
	}

	public void setProfile(byte[] profile) {
		this.profile = profile;
	}
}
