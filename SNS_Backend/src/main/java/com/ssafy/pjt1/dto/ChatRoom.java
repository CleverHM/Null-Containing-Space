package com.ssafy.pjt1.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.pjt1.service.ChatService;

@Entity
@Table(name = "ChatRoom")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int roomId;
	private String name;
	
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
