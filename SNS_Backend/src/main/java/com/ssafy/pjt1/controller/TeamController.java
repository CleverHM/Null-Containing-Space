package com.ssafy.pjt1.controller;

import java.util.List;
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
	
	@PostMapping("/team/binTeam")
	@ApiOperation(value = "1 번팀 생성", notes = "1번 팀 생성 기능을 구현")
	public void binTeam() {
		Team team = new Team("default 팀 입니다.", "default", Integer.MAX_VALUE);
		teamservice.join(team);
	}
	
	@PostMapping("/team/create")
	@ApiOperation(value = "팀 생성", notes = "팀 생성 기능을 구현")
	public void teamCreate(@Valid @RequestParam String title, String teamintro, int cnt, int prePro,List<Boolean> preTech, String nickname) {
		
		Optional<User> optionaluser = userservice.findtwo(nickname);
		User user = optionaluser.get();
		
		Team team = new Team(title, teamintro, cnt, prePro, preTech.get(0),  preTech.get(1), 
				preTech.get(2),  preTech.get(3),  preTech.get(4),  preTech.get(5),  
				preTech.get(6),  preTech.get(7),  preTech.get(8),  preTech.get(9), 
				preTech.get(10),  preTech.get(11),  preTech.get(12),  preTech.get(13),  preTech.get(14));
		

//		Team team = new Team(title, teamintro, cnt, prePro,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true);
		teamservice.join(team);
		
		
		user.setLeader(true);
		user.setTeam(team);
		userservice.signUp(user);
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
		Optional<Team> optionalTeam = teamservice.findone(1);
		
		Team team = optionalTeam.get();
		
		User user = optionalUser.get();
		
		user.setTeam(team);
		// 팀: 유저 끊어주기
		
		userservice.signUp(user);
	}
}
