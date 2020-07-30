package com.ssafy.pjt1.service;

import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.PostLike;

public interface LikeService {
	public void userLikePost(PostLike relation);
	public void userUnlikePost(int user, int post);
	public int likeCount(Post p);
}
