package com.ssafy.pjt1.model;

import java.util.List;

public class FeedData {
	private int pid;
	private String email;
	private String title;
	private String userName;
	private String fileName;
	private String fileUrl;
	private List<String> tags;

	public FeedData(int pid, String title,String email,String userName, String fileName, String fileUrl, List<String> tags) {
		this.pid = pid;
		this.email = email;
		this.title = title;
		this.userName = userName;
		this.fileName = fileName;
		this.fileUrl = fileUrl;
		this.tags = tags;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}