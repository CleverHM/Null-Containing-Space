package com.ssafy.pjt1.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Tagfollow")
//@IdClass(Tagfollowid.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagFollow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int tfid;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	
	@ManyToOne
	@JoinColumn(name = "TAG_ID")
	private Tag tag;


	public TagFollow() {

	}

	public TagFollow(User user, Tag tag) {
		this.user = user;
		this.tag = tag;
	}

	
	public User getUser() {
		return user;
	}

	
	public void setUser(User user) {
		this.user = user;
	}

	
	public Tag getTag() {
		return tag;
	}

	
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
}
