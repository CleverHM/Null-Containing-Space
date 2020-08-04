package com.ssafy.pjt1.model;

import java.util.Date;
import java.util.List;

import com.ssafy.pjt1.dto.Reply;

public class FeedDetailData {
    int pid;
    private String title;
    private String content;
    private Date date;
    
    private List<String> tags;
    
    private String userNickname;
    private String userEmail;

    private byte[] file;
    
    private int LikeCount;
    private int ViewCount;
    private int likeFlag;
    
    private List<ReplyData> replies;
    private int replyCount;
    
    public FeedDetailData(int pid, String title, String content, Date date, List<String> tags, String userNickname,
            String userEmail, byte[] file, int likeCount, int viewCount, int likeFlag, List<ReplyData> replies, int repltcount) {
        this.pid = pid;
        this.title = title;
        this.content = content;
        this.date = date;
        this.tags = tags;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
        this.file = file;
        this.LikeCount = likeCount;
        this.ViewCount = viewCount;
        this.likeFlag = likeFlag;
        this.replies = replies;
        this.replyCount = repltcount;
    }

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public List<ReplyData> getReplies() {
		return replies;
	}

	public void setReplies(List<ReplyData> replies) {
		this.replies = replies;
	}

	public int getLikeFlag() {
		return likeFlag;
	}

	public void setLikeFlag(int likeFlag) {
		this.likeFlag = likeFlag;
	}

	public int getViewCount() {
		return ViewCount;
	}

	public void setViewCount(int viewCount) {
		ViewCount = viewCount;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

	public int getLikeCount() {
		return LikeCount;
	}

	public void setLikeCount(int likeCount) {
		LikeCount = likeCount;
	}
    
    
    
}