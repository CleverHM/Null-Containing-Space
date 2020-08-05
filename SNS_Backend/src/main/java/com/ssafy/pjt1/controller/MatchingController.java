package com.ssafy.pjt1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.service.MatchingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "http://localhost:3000" })

@RestController
public class MatchingController {
	@Autowired
	private MatchingService matchingservice;
	
//	@PostMapping("/match/teammember")
//	@ApiOperation(value = "팀원 추천", notes = "매칭 알고리즘을 구현")
//	public Object matchingAlgo(@Valid @RequestParam String email, String teamName, List<Boolean> preferProject, List<Boolean> preferTech) {
//		Optional<User> u = userservice.findone(email);
//
////		matchingservice.match(request.getBack(), request.getFront(),request.getDatabase(),request.getFrame(),request.getAlgo());
//
//		ResponseEntity response = null;
//
//		return response;
//	}
}
