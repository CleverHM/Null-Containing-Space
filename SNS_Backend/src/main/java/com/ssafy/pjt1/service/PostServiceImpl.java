package com.ssafy.pjt1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt1.dao.PostDao;
import com.ssafy.pjt1.dto.Post;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	PostDao postdao;
	
	@Override
	public Optional<Post> findone(int postid){
		return postdao.findPostByPid(postid);	
	}

	@Override
	public List<Post> findall() {
		// TODO Auto-generated method stub
		return postdao.findAll();
	}
}
