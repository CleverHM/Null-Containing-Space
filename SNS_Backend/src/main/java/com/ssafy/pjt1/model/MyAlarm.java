package com.ssafy.pjt1.model;

import java.util.Date;

public class MyAlarm {
	private int aid;
	private String who;
	private Date createDate;
	private String content;
	private int teamid;
	private int pid;
	
	public MyAlarm(int aid, String who, Date createDate, String content, int teamid, int pid) {
		this.aid = aid;
		this.who = who;
		this.createDate = createDate;
		this.content = content;
		this.teamid = teamid;
		this.pid = pid;
	}
	
	public int getTeamid() {
		return teamid;
	}

	public void setTeamid(int teamid) {
		this.teamid = teamid;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
