package com.ssafy.pjt1.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Chat")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chat{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int cId;
	
	private String userName;
	private String content;
	
	//채팅 : 채팅방(N : 1관계)
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Chatroom room;
	
	public Chat() {
		
	}
	
	public Chat(String userName, String content, Chatroom room) {
		this.userName = userName;
		this.content = content;
		this.room = room;
	}

	
	public String getUserName() {
		return userName;
	}

	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	
	public int getcId() {
		return cId;
	}

	
	public void setcId(int cId) {
		this.cId = cId;
	}

	
	public Chatroom getRoom() {
		return room;
	}

	
	public void setRoom(Chatroom room) {
		this.room = room;
	}
}
