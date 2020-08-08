package com.ssafy.pjt1.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.dto.Alarm;
import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.Reply;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.service.AlarmService;
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
public class AlarmController {
	
	@Autowired
	private AlarmService alarmservice;
	@Autowired
    private UserService userservice;
	
	@PostMapping("/alarm/send")
    @ApiOperation(value = "알람 보내기 댓글작성", notes = "알람 보내기 기능을 구현.")
    public void userReply(@Valid @RequestParam String mynickname, String tonickname) throws Exception {
		Optional<User> optionalUser = userservice.findtwo(mynickname);
		
		User user = optionalUser.get();
		
		Alarm alarm = new Alarm("같이 해요", tonickname);
		
		alarm.setUser(user);
		
		alarmservice.sendAlarm(alarm);
	}
}
