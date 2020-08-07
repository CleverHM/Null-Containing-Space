package com.ssafy.pjt1.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.User;

public interface UserService {
	Optional<User> login(String email, String password);
	User signUp(User user);
	void delete(User selectUser, Set<Post> posts);
	Optional<User> duplNick(String nickName);
	Optional<User> findone(String email);
	Optional<User> findtwo(String nickname);
	Optional<User> findthree(int id);
	List<User> findall();
}
