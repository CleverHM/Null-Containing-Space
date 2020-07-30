package com.ssafy.pjt1.service;

import com.ssafy.pjt1.dto.TagFollow;
import com.ssafy.pjt1.dto.UserFollow;

public interface FollowService {
	public void followUser(UserFollow relation);
	public void unfollowUser(int from, int to);
	public void deleteUser(int userId);
	public void followTag(TagFollow relation);
	public TagFollow isFollowTag(int user, int tag);
}
