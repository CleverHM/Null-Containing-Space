package com.ssafy.pjt1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.pjt1.dto.Post;

public interface PostDao extends JpaRepository<Post, Integer>{

}
