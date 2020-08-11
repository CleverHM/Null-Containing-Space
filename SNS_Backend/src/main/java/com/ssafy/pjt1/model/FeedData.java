package com.ssafy.pjt1.model;

import java.util.Date;
import java.util.List;

public class FeedData {
    private int pid;
    private int LikeCount;
    private String email;
    private Date date;
    private String title;
    private String userName;
    private byte[] file;
    private int likeFlag;
    private int replyCount;
    private byte[] userFile;
    private int ViewCount;
    
    private List<String> tags;
    
    public FeedData(int pid, String email, Date date, String title,String userName, byte[] file, List<String> tags, int likeCount, int likeFlag, int replycount, byte[] userfile, int viewcount) {
        this.pid = pid;
        this.email = email;
        this.date = date;
        this.title = title;
        this.userName = userName;
        this.file = file;
        this.tags = tags;
        this.LikeCount = likeCount;
        this.likeFlag = likeFlag;
        this.replyCount = replycount;
        this.userFile = userfile;
        this.ViewCount = viewcount;
    }
    
    public FeedData(int pid, String email, Date date, String title,String userName, byte[] file, List<String> tags, int likeCount, int likeFlag, int replycount, byte[] userfile) {
        this.pid = pid;
        this.email = email;
        this.date = date;
        this.title = title;
        this.userName = userName;
        this.file = file;
        this.tags = tags;
        this.LikeCount = likeCount;
        this.likeFlag = likeFlag;
        this.replyCount = replycount;
        this.userFile = userfile;
    }

	public int getViewCount() {
		return ViewCount;
	}

	public void setViewCount(int viewCount) {
		ViewCount = viewCount;
	}

	public byte[] getUserFile() {
		return userFile;
	}

	public void setUserFile(byte[] userFile) {
		this.userFile = userFile;
	}

	public int getReplyCount() {
		return replyCount;
	}
	
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getLikeFlag() {
		return likeFlag;
	}


	public void setLikeFlag(int likeFlag) {
		this.likeFlag = likeFlag;
	}


	public int getLikeCount() {
		return LikeCount;
	}

	public void setLikeCount(int likeCount) {
		LikeCount = likeCount;
	}

	public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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