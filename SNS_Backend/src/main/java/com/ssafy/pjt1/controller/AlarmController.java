package com.ssafy.pjt1.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.dto.Alarm;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.model.MyAlarm;
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
	
	@PostMapping("/alarm/meAlarm")
    @ApiOperation(value = "나에게 온 메세지 확인", notes = "나에게 온 메세지 확인을 구현.")
    public List<MyAlarm> meAlarm(@Valid @RequestParam String mynickname) throws Exception {
		List<Alarm> aList = alarmservice.meAlarm(mynickname);
		List<MyAlarm> res = new LinkedList<MyAlarm>();
		
		for(int i = 0; i < aList.size(); i++) {
			MyAlarm ma = new MyAlarm(aList.get(i).getUser().getNickname(), aList.get(i).getCreateDate(), aList.get(i).getContent());
			res.add(ma);
		}
		
		return res;
	}
	
	// 보낸 알림함 확인
	@PostMapping("/alarm/meSendAlarm")
    @ApiOperation(value = "내가 보낸 알람 확인", notes = "내가 보낸 알람 구현.")
    public List<MyAlarm> meSendAlarm(@Valid @RequestParam String mynickname) throws Exception {
		Optional<User> optionalUser = userservice.findtwo(mynickname);
		User user = optionalUser.get();
		
		Set<Alarm> aList = user.getAlarms();
		List<MyAlarm> res = new LinkedList<MyAlarm>();
		
		for(Alarm a : aList) {
			MyAlarm ma = new MyAlarm(a.getToNickname(), a.getCreateDate(), a.getContent());
			res.add(ma);
		}
		
		return res;
	}
}
