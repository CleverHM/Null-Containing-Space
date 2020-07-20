package com.ssafy.pjt1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt1.dao.AuthDao;
import com.ssafy.pjt1.dto.Auth;

@Service
public class AuthServiceImpl implements AuthService{

	
	 @Autowired
	 AuthDao authdao;

	@Override
	public void insert(Auth auth) {
		// TODO Auto-generated method stub
		 authdao.save(auth);
	}
	 

}
