package com.ssafy.pjt1.model;

import java.util.Date;

public class MyAlarm {
	private String who;
	private Date createDate;
	private String content;
	
	public MyAlarm(String who, Date createDate, String content) {
		this.who = who;
		this.createDate = createDate;
		this.content = content;
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
