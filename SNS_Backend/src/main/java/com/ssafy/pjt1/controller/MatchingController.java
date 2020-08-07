package com.ssafy.pjt1.controller;

import java.util.ArrayList;
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
import com.ssafy.pjt1.service.MatchingService;
import com.ssafy.pjt1.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@RestController
public class MatchingController {
	@Autowired
	private UserService userservice;
	@Autowired
	private MatchingService matchingservice;
	
	@PostMapping("/match/teammember")
	@ApiOperation(value = "팀원 추천", notes = "매칭 알고리즘을 구현")
	public void matchingAlgo(@Valid @RequestParam String nickname) {
		List<String> preferTech = new ArrayList<>();
		List<User> userlist = new ArrayList<>();
		
		Optional<User> u = userservice.findtwo(nickname);
		
		Team team = u.get().getTeam();
		
		
		if(team.isBack_cpp()==true) preferTech.add("cpp");
		if(team.isBack_java()==true) preferTech.add("java");
		if(team.isBack_python()==true) preferTech.add("pyhton");
		if(team.isBack_php()==true) preferTech.add("php");
		if(team.isFront_html()==true) preferTech.add("html");
		if(team.isFront_css()==true) preferTech.add("css");
		if(team.isFront_javascript()==true) preferTech.add("javascript");
		if(team.isDb_sql()==true) preferTech.add("sql");
		if(team.isDb_nosql()==true) preferTech.add("nosql");
		if(team.isFrame_spring()==true) preferTech.add("spring");
		if(team.isFrame_django()==true) preferTech.add("django");
		if(team.isFrame_bootstrap()==true) preferTech.add("bootstrap");
		if(team.isFrame_vue()==true) preferTech.add("vue");
		if(team.isFrame_react()==true) preferTech.add("react");
		if(team.getAlgo()==true) preferTech.add("algo");
		
		int preferProject = team.getPreferProject();
		List<Integer> matching_user_id = matchingservice.match(preferProject, preferTech);
		
		for(Integer i : matching_user_id) {
			u = userservice.findthree(i);
			userlist.add(u.get());
		}
		
	}
}
