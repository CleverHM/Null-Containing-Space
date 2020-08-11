package com.ssafy.pjt1.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.pjt1.dto.Chatroom;

public interface ChatRoomDao extends JpaRepository<Chatroom, Integer> {
//	Optional<ChatRoom> findByroomId(int roomId);
}
