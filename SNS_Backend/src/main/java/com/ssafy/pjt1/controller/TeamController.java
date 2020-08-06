package com.ssafy.pjt1.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.dto.Team;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.model.TeamData;
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
	public void teamCreate(@Valid @RequestParam String title, String teamintro, int cnt, int prePro, Boolean[] preTech, String nickname) {
		
		Optional<Team> t = teamservice.findone(1);
		
		if(!t.isPresent()) {
			Team team = new Team("default 팀 입니다.", "default", Integer.MAX_VALUE);
			teamservice.join(team);
		}
		
		Optional<User> optionaluser = userservice.findtwo(nickname);
		User user = optionaluser.get();
		
		Team team = new Team(title, teamintro, cnt, prePro, preTech[0],  
				preTech[1], preTech[2],preTech[3],preTech[4],preTech[5],
				preTech[6],preTech[7],preTech[8],preTech[9],preTech[10],
				preTech[11],preTech[12],preTech[13],preTech[14]);
		

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
	
	// nick name 으로 프로젝트 페이지판별 하기
	@PostMapping("/team/exist")
	@ApiOperation(value = "페이지판별", notes = "페이지판별 기능을 구현")
	public Object exist(@Valid @RequestParam String nickname) {
		
		Optional<User> optionalUser = userservice.findtwo(nickname);
		User user = optionalUser.get();
		System.out.println(user.getTeam().getTeamid());
		
		TeamData teamdata = null;
		
		if(user.getTeam().getTeamid() == 1) {
			System.out.println("현재팀없음");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		else {
			
			// 팀 원 넣기
			List<String> mems = new LinkedList<String>();
			
			for(User u : user.getTeam().getUsers()) {
				mems.add(u.getNickname());
			}
			
			teamdata = new TeamData(user.getTeam().getCreateDate(), user.getTeam().getMemberCnt(), mems, user.getTeam().getTeamIntro(), user.getTeam().getTitle());
			return new ResponseEntity<>(teamdata, HttpStatus.OK);
		}

	}
}
