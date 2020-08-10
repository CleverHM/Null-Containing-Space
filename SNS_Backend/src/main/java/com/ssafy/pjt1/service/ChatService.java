package com.ssafy.pjt1.service;

import java.util.List;

import org.springframework.web.socket.WebSocketSession;

import com.ssafy.pjt1.dto.ChatRoom;

public interface ChatService {
	public List<ChatRoom> findAllRoom();
	public ChatRoom findRoomById(String id);
	public ChatRoom createChatRoom(String name);
}
