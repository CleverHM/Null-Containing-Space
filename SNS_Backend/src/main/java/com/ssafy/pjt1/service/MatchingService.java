package com.ssafy.pjt1.service;

import java.util.List;

import com.ssafy.pjt1.dto.User;

public interface MatchingService {
	public List<Integer> match(List<String> back, List<String> front, List<String> database, List<String> frame, String algo);
}
