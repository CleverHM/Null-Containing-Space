package com.ssafy.pjt1.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "ChatRoom")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int roomId;
	
	
	private String name;
	
	// 채팅방 : 채팅  (1 : N 관계)
	@JsonIgnore
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Chat> chat;
	
	public ChatRoom() {
		
	}
	
	public ChatRoom(String name) {
		this.name = name;
	}
	
	public int getRoomId() {
		return roomId;
	}

	
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}
}
