package com.ssafy.pjt1.service;

import java.util.Optional;

import com.ssafy.pjt1.dto.User;

public interface UserService {
	Optional<User> login(String email, String password);

	User signUp(User user);

	void delete(User selectUser);

	Optional<User> findone(String email);
}
