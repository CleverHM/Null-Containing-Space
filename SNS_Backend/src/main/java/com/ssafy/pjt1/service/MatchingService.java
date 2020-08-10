package com.ssafy.pjt1.service;

import java.util.List;

import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.service.MatchingServiceImpl.U;

public interface MatchingService {
	public List<U> match(int preferProject, List<String> preferTech);
}
