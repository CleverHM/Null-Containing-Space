package com.ssafy.pjt1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.pjt1.dto.ChatRoom;

public interface ChatRoomDao extends JpaRepository<ChatRoom, Integer> {

}
