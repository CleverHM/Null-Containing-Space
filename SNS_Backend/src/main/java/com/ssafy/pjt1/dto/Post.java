package com.ssafy.pjt1.dto;

import java.awt.Image;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Post")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int pid;
	private String title;
	private String content;
	
	//게시물 : 유저 (N : 1 관계)
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	//게시물 : 이미지(1 : 1 관계)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FILES_ID")
	private Files files;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<PostTag> posttags;

	public Post() {

	}
	 
	public Post(int pid, String title, String content, Set<PostTag> posttags, User user, Files files) {
		this.pid = pid;
		this.title = title;
		this.content = content;
		this.posttags = posttags;
		this.user = user;
		this.files = files;
	}


	public Post(String title, String content, Files files) {
		this.title = title;
		this.content = content;
		this.files = files;
	}
	
	
	
	public Set<PostTag> getPosttags() {
		return posttags;
	}

	public void setPosttags(Set<PostTag> posttags) {
		this.posttags = posttags;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
	public Files getFiles() {
		return files;
	}

	public void setFiles(Files files) {
		this.files = files;
	}

}
