package com.ssafy.pjt1.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Postlike")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostLike {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int plid;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "POST_ID")
	private Post post;

	public PostLike() {}
	
	public PostLike(int plid, User user, Post post) {
		this.plid = plid;
		this.user = user;
		this.post = post;
	}

	public int getPlid() {
		return plid;
	}

	public void setPlid(int plid) {
		this.plid = plid;
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
		
}
