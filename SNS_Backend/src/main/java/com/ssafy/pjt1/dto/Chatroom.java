package com.ssafy.pjt1.dto;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Chatroom")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chatroom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int roomId;

	
	// 채팅방 : 채팅  (1 : N 관계)
	@JsonIgnore
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Chat> chat = new LinkedList<Chat>();
	
	// 채팅방 : 팀 (1 : 1 관계)
	@OneToOne(mappedBy = "chatroom")
	private Team team;
	
	public Chatroom() {
		
	}
	
	public int getRoomId() {
		return roomId;
	}

	
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	
	public List<Chat> getChat() {
		return chat;
	}

	
	public void setChat(List<Chat> chat) {
		this.chat = chat;
	}

	
	public Team getTeam() {
		return team;
	}

	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	

}
