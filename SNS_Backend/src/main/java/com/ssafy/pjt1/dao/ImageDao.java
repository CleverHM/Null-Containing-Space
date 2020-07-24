package com.ssafy.pjt1.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.pjt1.dto.Image;

public interface ImageDao extends JpaRepository<Image, Integer> {
	Optional<Image> findByIid(int iid);
}
