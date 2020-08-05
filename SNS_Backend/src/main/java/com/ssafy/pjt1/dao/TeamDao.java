package com.ssafy.pjt1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.pjt1.dto.Team;

public interface TeamDao extends JpaRepository<Team, Integer>{

}
