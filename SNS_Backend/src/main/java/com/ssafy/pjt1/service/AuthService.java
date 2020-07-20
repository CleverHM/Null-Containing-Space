package com.ssafy.pjt1.service;

import java.util.Optional;

import com.ssafy.pjt1.dto.Auth;
import com.ssafy.pjt1.dto.User;

public interface AuthService {
	void insert(Auth auth);
	Optional<Auth> findone(String email);
}
