package com.ssafy.pjt1.service;

<<<<<<< HEAD
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

=======
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
>>>>>>> backend
}
