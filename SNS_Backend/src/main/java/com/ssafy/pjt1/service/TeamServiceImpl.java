package com.ssafy.pjt1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt1.dao.TeamDao;
import com.ssafy.pjt1.dto.Team;

@Service
public class TeamServiceImpl implements TeamService{

	@Autowired
	TeamDao teamDao;
	
	@Override
	public Team join(Team team) {
		return teamDao.save(team);
	}

	@Override
	public Optional<Team> findone(int teamid) {
		return teamDao.findById(teamid);
	}

}
