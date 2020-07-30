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
@Table(name = "Posttag")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostTag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int ptid;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "POST_ID")
	private Post post;
	
	@ManyToOne
	@JoinColumn(name = "TAG_ID")
	private Tag tag;

	public PostTag() {
		
	}

	public PostTag(int ptid, Post post, Tag tag) {
		this.ptid = ptid;
		this.post = post;
		this.tag = tag;
	}

	public int getPtid() {
		return ptid;
	}

	public void setPtid(int ptid) {
		this.ptid = ptid;
	}

	public Post getPost() {
		return post;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}
	
	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
}
