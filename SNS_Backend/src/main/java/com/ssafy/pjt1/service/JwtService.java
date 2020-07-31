package com.ssafy.pjt1.service;

import java.util.Map;

import com.ssafy.pjt1.exception.UnauthorizedException;

public interface JwtService {

	public <T> String create(String email, String nickname);
	public boolean isUsable(String jwt) throws UnauthorizedException;
	public Map<String, Object> get(String key) throws UnauthorizedException;
}
