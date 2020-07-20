package com.ssafy.pjt1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt1.dao.UserDao;
import com.ssafy.pjt1.dto.User;

@Service
public class UserServiceImpl implements UserService {
	 @Autowired
	 UserDao userdao;

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
		userdao.delete(user);
	}





}
