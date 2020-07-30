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
@Table(name = "Userfollow")
//@IdClass(Tagfollowid.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserFollow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int ufid;
	
	@ManyToOne
	@JoinColumn(name = "FROM_ID")
	private User from;
	
	@ManyToOne
	@JoinColumn(name = "TO_ID")
	private User to;
	
	public UserFollow() {
		
	}

	public UserFollow(User from, User to) {
		this.from = from;
		this.to = to;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}
	
}
