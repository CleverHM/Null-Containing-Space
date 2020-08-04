package com.ssafy.pjt1.model;

import java.util.List;

public class MyPageData {
	private String nickname;
	private int followerCnt;
	private int followingCnt;
	private String blogaddr;
	private String gitaddr;
	private String intro;
	private List<String> tag;
	private List<Integer> abt;
	
	private byte[] file;

	public MyPageData(String nickname, int followerCnt, int followingCnt, String blogaddr, String gitaddr, String intro,
			List<String> tag, List<Integer> abt, byte[] file) {
		this.nickname = nickname;
		this.followerCnt = followerCnt;
		this.followingCnt = followingCnt;
		this.blogaddr = blogaddr;
		this.gitaddr = gitaddr;
		this.intro = intro;
		this.tag = tag;
		this.abt = abt;
		this.file = file;
	}
	
	public MyPageData(String nickname, int followerCnt, int followingCnt, String blogaddr, String gitaddr, String intro,
			List<String> tag, List<Integer> abt) {
		this.nickname = nickname;
		this.followerCnt = followerCnt;
		this.followingCnt = followingCnt;
		this.blogaddr = blogaddr;
		this.gitaddr = gitaddr;
		this.intro = intro;
		this.tag = tag;
		this.abt = abt;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getFollowerCnt() {
		return followerCnt;
	}

	public void setFollowerCnt(int followerCnt) {
		this.followerCnt = followerCnt;
	}

	public int getFollowingCnt() {
		return followingCnt;
	}

	public void setFollowingCnt(int followingCnt) {
		this.followingCnt = followingCnt;
	}

	public String getBlogaddr() {
		return blogaddr;
	}

	public void setBlogaddr(String blogaddr) {
		this.blogaddr = blogaddr;
	}

	public String getGitaddr() {
		return gitaddr;
	}

	public void setGitaddr(String gitaddr) {
		this.gitaddr = gitaddr;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public List<String> getTag() {
		return tag;
	}

	public void setTag(List<String> tag) {
		this.tag = tag;
	}

	public List<Integer> getAbt() {
		return abt;
	}

	public void setAbt(List<Integer> abt) {
		this.abt = abt;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}
	
	
}
