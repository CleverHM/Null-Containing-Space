package com.ssafy.pjt1.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.pjt1.dto.Profile;

public interface ProfileDao extends JpaRepository<Profile, Integer> {
	Optional<Profile> findByPfid(int pfid);
}
