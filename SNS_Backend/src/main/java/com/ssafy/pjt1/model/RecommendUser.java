package com.ssafy.pjt1.model;

import java.util.Date;

public class RecommendUser {
	private int uid;
	private byte[] file;
	private String nickname;
	private Date createDate;
	private int follwerCount;
	private int followingCount;
	
	public RecommendUser(int uid, byte[] file, String nickname, Date createDate, int follwerCount, int followingCount) {
		this.uid = uid;
		this.file = file;
		this.nickname = nickname;
		this.createDate = createDate;
		this.follwerCount = follwerCount;
		this.followingCount = followingCount;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getFollwerCount() {
		return follwerCount;
	}

	public void setFollwerCount(int follwerCount) {
		this.follwerCount = follwerCount;
	}

	public int getFollowingCount() {
		return followingCount;
	}

	public void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}
	
}
