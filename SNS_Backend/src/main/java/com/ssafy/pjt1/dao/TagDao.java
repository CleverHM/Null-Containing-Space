package com.ssafy.pjt1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.pjt1.dto.Tag;

public interface TagDao extends JpaRepository<Tag, Integer> {

}
