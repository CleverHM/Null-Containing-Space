package com.ssafy.pjt1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt1.dao.ReplyDao;
import com.ssafy.pjt1.dto.Reply;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	ReplyDao replydao;
	
	@Override
	public Optional<Reply> findone(int replyid){
		return replydao.findReplyByRid(replyid);	
	}
}	
