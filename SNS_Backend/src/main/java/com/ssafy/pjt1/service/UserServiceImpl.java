package com.ssafy.pjt1.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt1.dao.PostDao;
import com.ssafy.pjt1.dao.PostTagDao;
import com.ssafy.pjt1.dao.UserDao;
import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userdao;
	
	@Autowired
	PostDao postdao;
	
	@Autowired
	FollowService followservice;
	
	@Autowired
	LikeService postservice;
	
	@Autowired
	PostTagDao posttagdao;

	@Override
	public Optional<User> login(String email, String password) {
		// TODO Auto-generated method stub
		return userdao.findUserByEmailAndPassword(email, password);
	}

	@Override
	public User signUp(User user) {
		return userdao.save(user);
	}

	@Override
	public Optional<User> findone(String email) {
		return userdao.findUserByEmail(email);
	}

	@Override
	public void delete(User user, Set<Post> posts) {
		
// 회원탈퇴시 회원 팔로우 관계 다끊어주기  && 회원탈퇴시 태그 팔로우 관계 다 끊어주기.
		//followservice.deleteUser(user.getUid());
// 회원탈퇴시 회원이 올린 게시물과 올린 태그들과의 관계 다끊어주기.
		//for(Post p : posts) posttagdao.deleteUser2(p.getPid());
		
		userdao.delete(user);
	}

}
