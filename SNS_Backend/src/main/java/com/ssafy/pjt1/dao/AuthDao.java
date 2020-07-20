package com.ssafy.pjt1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.pjt1.dto.Auth;

public interface AuthDao extends JpaRepository<Auth, String> {

}
