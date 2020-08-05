package com.ssafy.pjt1.service;

import java.util.Optional;

import com.ssafy.pjt1.dto.Team;
import com.ssafy.pjt1.dto.User;

public interface TeamService {
	Team join(Team team);
	Optional<Team> findone(int teamid);
}
