package com.ssafy.pjt1.model;

import java.util.Date;
import java.util.List;

public class TeamData {
	private Date createDate;
	private int cnt;
	private List<String> members;
	private String intro;
	private String title;
	private int preferProject;
	private List<Boolean> preTech;
	private String leaderNickname;

	public TeamData(Date createDate, int cnt, List<String> members, String intro, String title, int preProject, List<Boolean> preTech, String leader ) {
		this.createDate = createDate;
		this.cnt = cnt;
		this.members = members;
		this.intro = intro;
		this.title = title;
		this.preferProject = preProject;
		this.preTech = preTech;
		this.leaderNickname = leader;
	}
	
	public String getLeaderNickname() {
		return leaderNickname;
	}

	public void setLeaderNickname(String leaderNickname) {
		this.leaderNickname = leaderNickname;
	}

	public List<Boolean> getPreTech() {
		return preTech;
	}

	public void setPreTech(List<Boolean> preTech) {
		this.preTech = preTech;
	}

	public int getPreferProject() {
		return preferProject;
	}

	public void setPreferProject(int preferProject) {
		this.preferProject = preferProject;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(List<String> members) {
		this.members = members;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
