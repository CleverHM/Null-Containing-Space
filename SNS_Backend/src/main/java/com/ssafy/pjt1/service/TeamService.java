package com.ssafy.pjt1.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.pjt1.dto.Team;

public interface TeamService {
	Team join(Team team);
	Optional<Team> findone(int teamid);
	List<Team> fineall();
}
