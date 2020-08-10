package com.ssafy.pjt1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
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
import com.ssafy.pjt1.model.MatchingData;
import com.ssafy.pjt1.model.MatchingMemberData;
import com.ssafy.pjt1.service.MatchingService;
import com.ssafy.pjt1.service.MatchingServiceImpl.U;
import com.ssafy.pjt1.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })


//@CrossOrigin(origins = { "http://localhost:3000" })
@CrossOrigin(origins = "*")

@RestController
public class MatchingController {
	@Autowired
	private UserService userservice;
	@Autowired
	private MatchingService matchingservice;

	
	@PostMapping("/match/teammember")
	@ApiOperation(value = "팀원 추천", notes = "매칭 알고리즘을 구현")
	public MatchingData matchingAlgo(@Valid @RequestParam String nickname) throws FileNotFoundException, IOException {
		List<String> preferTech = new ArrayList<>();
		List<User> userlist1 = new ArrayList<>();
		List<User> userlist2 = new ArrayList<>();
		List<Integer> plist = new ArrayList<>();
		
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
		List<U> matching_user = new ArrayList<>();
		matching_user = matchingservice.match(preferProject, preferTech);

		Optional<User> u2;
		
		for(U i : matching_user) {
			u2 = userservice.findthree(i.getUserid());
			if(i.isPrefer() == true) userlist1.add(u2.get());
			else {
				userlist2.add(u2.get());
				plist.add(i.getPercent());
			}
		}
		
		List<MatchingMemberData> res1 = new LinkedList<>();
		
		for (int i = 0; i < userlist1.size(); i++) {
			byte[] trash = null;

			File result = new File(userlist1.get(i).getProfile().getFileurl()+ userlist1.get(i).getProfile().getFilename());

			if (result.exists()) {
				System.out.println("프로필 사진 있음");
				InputStream inputStream = new FileInputStream(userlist1.get(i).getProfile().getFileurl() + userlist1.get(i).getProfile().getFilename());
				byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);
				
				res1.add(new MatchingMemberData(userlist1.get(i).getUid(), userlist1.get(i).getNickname(), userlist1.get(i).getPreferProject(), 0, out));
			}
			else res1.add(new MatchingMemberData(userlist1.get(i).getUid(), userlist1.get(i).getNickname(), userlist1.get(i).getPreferProject(), 0, trash));
		}
		
		List<MatchingMemberData> res2 = new LinkedList<>();
		
		for (int i = 0; i < userlist2.size(); i++) {
			byte[] trash = null;

			File result = new File(userlist2.get(i).getProfile().getFileurl()+ userlist2.get(i).getProfile().getFilename());

			if (result.exists()) {
				System.out.println("프로필 사진 있음");
				InputStream inputStream = new FileInputStream(userlist2.get(i).getProfile().getFileurl() + userlist2.get(i).getProfile().getFilename());
				byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);
				
				res2.add(new MatchingMemberData(userlist2.get(i).getUid(), userlist2.get(i).getNickname(), userlist2.get(i).getPreferProject(), plist.get(i), out));
			}
			else res2.add(new MatchingMemberData(userlist2.get(i).getUid(), userlist2.get(i).getNickname(), userlist2.get(i).getPreferProject(), plist.get(i), trash));
		}
		
		
		
		System.out.println("유저 uid : " + u.get().getUid());
		System.out.println("유저 이메일 : " + u.get().getEmail());
		System.out.println("유저 닉네임 : " + u.get().getNickname());
		System.out.println("매칭 돌린 애가 리더인지 아닌지 : " + u.get().getLeader());
		MatchingData res = new MatchingData(u.get().getLeader(), res1, res2);
		
		System.out.println("추천된 선호 팀원들 : ");
		for(MatchingMemberData r : res1)System.out.println(r);
		System.out.println("추천된 선호 안하는 팀원들: ");
		for(MatchingMemberData r : res2)System.out.println(r);
		
		return res;
	}
}
