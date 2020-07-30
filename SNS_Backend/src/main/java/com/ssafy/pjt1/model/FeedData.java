package com.ssafy.pjt1.model;

import java.io.File;
import java.util.List;

public class FeedData {
<<<<<<< HEAD
	private int pid;
	private String email;
	private String date;
	private String title;
	private String userName;
	private String fileName;
	private String fileUrl;
	private List<String> tags;

	public FeedData(int pid, String email, String date, String title,String userName, String fileName, String fileUrl, List<String> tags) {
		this.pid = pid;
		this.email = email;
		this.date = date;
		this.title = title;
		this.userName = userName;
		this.fileName = fileName;
		this.fileUrl = fileUrl;
		this.tags = tags;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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
=======
    private int pid;
    private int LikeCount;
    private String email;
    private String date;
    private String title;
    private String userName;
    private byte[] file;
    private List<String> tags;

    public FeedData(int pid, String email, String date, String title,String userName, byte[] file, List<String> tags, int likeCount) {
        this.pid = pid;
        this.email = email;
        this.date = date;
        this.title = title;
        this.userName = userName;
        this.file = file;
        this.tags = tags;
        this.LikeCount = likeCount;
    }
>>>>>>> backend

    public int getLikeCount() {
		return LikeCount;
	}

	public void setLikeCount(int likeCount) {
		LikeCount = likeCount;
	}

	public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
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