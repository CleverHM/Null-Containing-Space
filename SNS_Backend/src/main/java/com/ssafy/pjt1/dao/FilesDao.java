package com.ssafy.pjt1.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.pjt1.dto.Files;

public interface FilesDao extends JpaRepository<Files, Integer> {
	Optional<Files> findByFid(int fid);
}
