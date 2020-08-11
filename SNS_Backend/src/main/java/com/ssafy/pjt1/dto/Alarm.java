package com.ssafy.pjt1.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Alarm")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Alarm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int aid;
	
	private String content;
	private String toNickname;
	
	// 1: 팀, 2: 댓글, 3: 팔로우
	private int flag;
	
	//댓글 : 유저 (N : 1 관계)
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@CreationTimestamp
	@Column(updatable = false)
	private Date createDate;	
	
	public Alarm() {
		
	}
	
	public Alarm( String content, String toNickname, int flag) {
		this.content = content;
		this.toNickname = toNickname;
		this.flag = flag;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getToNickname() {
		return toNickname;
	}

	public void setToNickname(String toNickname) {
		this.toNickname = toNickname;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
