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
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

	//게시물 : 태그 (N : N 관계)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "Posttag", joinColumns = {
	@JoinColumn(name = "POST_ID") }, inverseJoinColumns = {
	@JoinColumn(name = "TAG_ID")})
	private Set<Tag> tags = new HashSet<Tag>();

	//게시물 : 유저 (N : 1 관계)
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	private User user;
	
	//게시물 : 이미지(1 : 1 관계)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FILES_ID")
	private Files files;

	public Post() {

	}
	
	
	public Post(String title, String content, Set<Tag> tags, User user, Files files) {
		this.title = title;
		this.content = content;
		this.tags = tags;
		this.user = user;
		this.files = files;
	}


	public Post(String title, String content, Files files) {
		this.title = title;
		this.content = content;
		this.files = files;
	}
	
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
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

	public Files getImg() {
		return files;
	}

	public void setImg(Files files) {
		this.files = files;
	}
}
