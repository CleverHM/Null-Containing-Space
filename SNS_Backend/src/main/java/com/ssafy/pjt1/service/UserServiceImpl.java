package com.ssafy.pjt1.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt1.dao.PostDao;
import com.ssafy.pjt1.dao.UserDao;
import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userdao;
	
	@Autowired
	PostDao postdao;

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
		// TODO Auto-generated method stub
		System.out.println(email);
		return userdao.findUserByEmail(email);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		userdao.deletefollow1(user.getUid());
        userdao.deletefollow2(user.getUid());
        userdao.deletetag(user.getUid());
        //postdao.deletetag(post.getPid());
		userdao.delete(user);
	}
	
	@Override
    public void delete1(Set<Post> posts) {
        for(Post p : posts) userdao.deleteposttag(p.getPid());
    }

}
