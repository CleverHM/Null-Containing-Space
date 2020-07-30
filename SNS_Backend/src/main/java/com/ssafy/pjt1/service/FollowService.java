package com.ssafy.pjt1.service;

import com.ssafy.pjt1.dto.TagFollow;
<<<<<<< HEAD
=======
import com.ssafy.pjt1.dto.User;
>>>>>>> backend
import com.ssafy.pjt1.dto.UserFollow;

public interface FollowService {
	public void followUser(UserFollow relation);
	public void unfollowUser(int from, int to);
<<<<<<< HEAD
	public void deleteUser(int userId);
	public void followTag(TagFollow relation);
	public TagFollow isFollowTag(int user, int tag);
=======
	//public void deleteUser(int userId);
	public void followTag(TagFollow relation);
	public TagFollow isFollowTag(int user, int tag);
	public int followerCount(User u);
	public int followingCount(User u);
>>>>>>> backend
}
