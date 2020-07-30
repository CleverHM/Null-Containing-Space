package com.ssafy.pjt1.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt1.dao.PostLikeDao;
import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.PostLike;

@Service
public class LikeServiceImpl implements LikeService {
	@Autowired
	PostLikeDao postlikedao;	
	
	// 유저가 게시물을 좋아요 하는 기능
	@Override
	public void userLikePost(PostLike relation) {
		postlikedao.save(relation);
	}
	
	//유저가 게시물을 좋아요를 취소하는 기능
	@Override
	public void userUnlikePost(int user, int post) {
		postlikedao.deleteRelation(user, post);
	}
	
	@Override
	public int likeCount(Post p) {
		Set<PostLike> pl = p.getPostlikes();
		return pl.size();
	}
	
}
