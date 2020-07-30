package com.ssafy.pjt1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt1.dao.TagFollowDao;
import com.ssafy.pjt1.dao.UserFollowDao;
import com.ssafy.pjt1.dto.TagFollow;
import com.ssafy.pjt1.dto.UserFollow;

@Service
public class FollowServiceImpl implements FollowService {
	@Autowired
	UserFollowDao userfollowdao;

	@Autowired
	TagFollowDao tagfollowdao;

	// 유저 팔로우 기능.
	@Override
	public void followUser(UserFollow relation) {
		userfollowdao.save(relation);
	}

	// 유저 언팔로우 기능.
	@Override
	public void unfollowUser(int from, int to) {
		userfollowdao.deleteRelation(from, to);
	}

	// 회원 탈퇴시 팔로우 관계를 모두 삭제해주는 작업.
	public void deleteUser(int userId) {
		int from, to;
		from = to = userId;
		
		// 회원 탈퇴시 유저간에 팔로우 관계를 끊어주는 작업.
		userfollowdao.deleteByFrom(from);
		System.out.println("deleteUser1");
		userfollowdao.deleteByTo(to);
		System.out.println("deleteUser2");
		
		// 회원 탈퇴시 유저가 팔로우한 태그들 관계를 끊어주는 작업.
		tagfollowdao.deleteRelation(userId);
		
	}

	// 태그 팔로우 기능.
	@Override
	public void followTag(TagFollow relation) {
		tagfollowdao.save(relation);
	}

	// 특정 태그를 팔로우하고 있는지 알아보는 기능.
	@Override
	public TagFollow isFollowTag(int user, int tag) {
		return tagfollowdao.findRelation(user, tag);
	}

}
