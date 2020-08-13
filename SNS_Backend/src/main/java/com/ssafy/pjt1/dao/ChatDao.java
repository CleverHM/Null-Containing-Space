package com.ssafy.pjt1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.pjt1.dto.Chat;

public interface ChatDao extends JpaRepository<Chat, Integer>{

}
