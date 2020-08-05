package com.ssafy.pjt1.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.dto.Team;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.service.TeamService;
import com.ssafy.pjt1.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "http://localhost:3000" })

@RestController
public class TeamController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private TeamService teamservice;
	
	@PostMapping("/team/ceate")
	@ApiOperation(value = "팀 생성", notes = "팀 생성 기능을 구현")
	public void nickNameDuplicate(@Valid @RequestParam String title, String teamintro, int cnt) {
		Team team = new Team(title, teamintro, cnt);
		teamservice.join(team);
	}
	
	@PostMapping("/team/join")
	@ApiOperation(value = "팀 가입", notes = "팀 가입 기능을 구현")
	public void teamjoin(@Valid @RequestParam String nickname, int teamid) {
		System.out.println(nickname);
		Optional<User> optionalUser = userservice.findtwo(nickname);
		Optional<Team> optionalTeam = teamservice.findone(teamid);
		
		User user = optionalUser.get();
		Team team = optionalTeam.get();
		
		// 팀: 유저 이어주기
		user.setTeam(team);
		
		userservice.signUp(user);
	}
	
	@PostMapping("/team/leave")
	@ApiOperation(value = "팀 탈퇴", notes = "팀 탈퇴 기능을 구현")
	public void teamleave(@Valid @RequestParam String nickname) {
		Optional<User> optionalUser = userservice.findtwo(nickname);
		Team team = optionalUser.get().getTeam();
		
		User user = optionalUser.get();
		
		// 팀: 유저 끊어주기
		
		
		userservice.signUp(user);
	}
}
