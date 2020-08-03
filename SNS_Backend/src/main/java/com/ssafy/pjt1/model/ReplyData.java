package com.ssafy.pjt1.model;

import java.util.Date;

public class ReplyData {
	private int rid;
	private String email;
	private String content;
	private String who;
	private Date createData;
	
	public ReplyData(int rid, String content, String who, Date createData, String email) {
		this.rid = rid;
		this.content = content;
		this.who = who;
		this.createData = createData;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWho() {
		return who;
	}

	public void setWho(String who) {
		this.who = who;
	}

	public Date getCreateData() {
		return createData;
	}

	public void setCreateData(Date createData) {
		this.createData = createData;
	}
	
	
}
