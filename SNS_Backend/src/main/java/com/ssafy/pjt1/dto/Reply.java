package com.ssafy.pjt1.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Reply")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int rid;
	private String content;
	
	//댓글 : 유저 (N : 1 관계)
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	//댓글 : 게시글(N : 1관계)
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;
	
	@CreationTimestamp
	@Column(updatable = false)
	private Date createDate;

	public Reply() {}
	
	public Reply(int rid, String content, User user, Post post, Date createDate) {
		this.rid = rid;
		this.content = content;
		this.user = user;
		this.post = post;
		this.createDate = createDate;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
