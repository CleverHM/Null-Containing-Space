package com.ssafy.pjt1.model;

import java.util.Date;

public class ReplyData {
	private int rid;
	private int uid;
	private String content;
	private String who;
	private Date createData;
	
	public ReplyData(int rid, String content, String who, Date createData, int uid) {
		this.rid = rid;
		this.content = content;
		this.who = who;
		this.createData = createData;
		this.uid = uid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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
