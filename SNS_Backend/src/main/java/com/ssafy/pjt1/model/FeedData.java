package com.ssafy.pjt1.model;

import java.time.LocalDateTime;
import java.util.List;

public class FeedData {
	private int pid;
	private String email;
	private LocalDateTime date;
	private String title;
	private String userName;
	private byte[] file;
	private List<String> tags;

	public FeedData(int pid, String email, LocalDateTime localDateTime, String title,String userName, byte[] file, List<String> tags) {
		this.pid = pid;
		this.email = email;
		this.date = localDateTime;
		this.title = title;
		this.userName = userName;
		this.file = file;
		this.tags = tags;
	}


	public byte[] getFile() {
		return file;
	}


	public void setFile(byte[] file) {
		this.file = file;
	}


	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}