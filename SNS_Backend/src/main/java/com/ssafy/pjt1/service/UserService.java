package com.ssafy.pjt1.service;

import java.util.Optional;
import java.util.Set;

import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.User;

public interface UserService {
	Optional<User> login(String email, String password);

	User signUp(User user);

	void delete(User selectUser);

	Optional<User> findone(String email);
	
	void delete1(Set<Post> posts);
}
