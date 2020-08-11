package com.ssafy.pjt1.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.pjt1.dto.Post;

public interface PostService {
	Optional<Post> findone(int postid);
	List<Post> findall();
}
