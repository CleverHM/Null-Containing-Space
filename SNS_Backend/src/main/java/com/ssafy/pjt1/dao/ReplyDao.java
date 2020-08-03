package com.ssafy.pjt1.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.pjt1.dto.Reply;

public interface ReplyDao extends JpaRepository<Reply, Integer>{
	Optional<Reply> findReplyByRid(int replyid);
}
