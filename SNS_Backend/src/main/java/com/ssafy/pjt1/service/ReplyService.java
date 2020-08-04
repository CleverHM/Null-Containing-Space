package com.ssafy.pjt1.service;

import java.util.Optional;

import com.ssafy.pjt1.dto.Reply;

public interface ReplyService {
	Optional<Reply> findone(int replyid);
}
