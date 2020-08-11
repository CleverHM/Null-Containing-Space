package com.ssafy.pjt1.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.dto.Alarm;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.AlarmResponse;
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

	// 팀알람
	@PostMapping("/alarm/teamAlarm")
	@ApiOperation(value = "팀알람 보내기", notes = "팀알람 보내기 기능을 구현.")
	public void teamAlarm(@Valid @RequestParam String mynickname, String tonickname) throws Exception {
		Optional<User> optionalUser = userservice.findtwo(mynickname);

		User user = optionalUser.get();

		Alarm alarm = new Alarm(mynickname + "님이 프로젝트 같이하기를 요청했습니다. ", tonickname, 1);

		alarm.setUser(user);

		alarmservice.sendAlarm(alarm);
	}

	// 댓글알람
	@PostMapping("/alarm/replyAlarm")
	@ApiOperation(value = "댓글알람 보내기", notes = "댓글알람 보내기 기능을 구현.")
	public void replyAlarm(@Valid @RequestParam String mynickname, String tonickname, String title) throws Exception {
		Optional<User> optionalUser = userservice.findtwo(mynickname);

		User user = optionalUser.get();

		Alarm alarm = new Alarm(mynickname + "님이 회원님의 '" + title + "' 게시글에 댓글을 달았습니다. ", tonickname, 2);

		alarm.setUser(user);

		alarmservice.sendAlarm(alarm);
	}

	// 팔로우알람
	@PostMapping("/alarm/followAlarm")
	@ApiOperation(value = "팔로우알람 보내기", notes = "팔로우알람 보내기 기능을 구현.")
	public void followAlarm(@Valid @RequestParam String mynickname, String tonickname) throws Exception {
		Optional<User> optionalUser = userservice.findtwo(mynickname);

		User user = optionalUser.get();

		Alarm alarm = new Alarm(mynickname + "님이 팔로우 했습니다. ", tonickname, 3);

		alarm.setUser(user);

		alarmservice.sendAlarm(alarm);
	}

	
	@PostMapping("/alarm/meAlarm")
	@ApiOperation(value = "나에게 온 메세지 확인", notes = "나에게 온 메세지 확인을 구현.")
	public Object meAlarm(@Valid @RequestParam String mynickname) throws Exception {
		
		List<Alarm> aList = alarmservice.meAlarm(mynickname);

		List<MyAlarm> teamalarm = new LinkedList<MyAlarm>();
		List<MyAlarm> snsalarm = new LinkedList<MyAlarm>();

		final AlarmResponse result = new AlarmResponse();
		
		for (int i = 0; i < aList.size(); i++) {
			MyAlarm ma = new MyAlarm(aList.get(i).getAid(), aList.get(i).getUser().getNickname(),
					aList.get(i).getCreateDate(), aList.get(i).getContent(), aList.get(i).getUser().getTeam().getTeamid());
			
			if(aList.get(i).getFlag() == 1) {
				teamalarm.add(ma);
			} else {
				snsalarm.add(ma);
			}
		}

		result.status = true;
		result.data = "success";
		result.teamalarm = teamalarm;
		result.snsalarm = snsalarm;
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 보낸 알림함 확인
	@PostMapping("/alarm/meSendAlarm")
	@ApiOperation(value = "내가 보낸 알람 확인", notes = "내가 보낸 알람 구현.")
	public Object meSendAlarm(@Valid @RequestParam String mynickname) throws Exception {
		Optional<User> optionalUser = userservice.findtwo(mynickname);
		User user = optionalUser.get();

		Set<Alarm> aList = user.getAlarms();
		List<MyAlarm> teamalarm = new LinkedList<MyAlarm>();
		List<MyAlarm> snsalarm = new LinkedList<MyAlarm>();

		final AlarmResponse result = new AlarmResponse();
		
		for (Alarm a : aList) {
			MyAlarm ma = new MyAlarm(a.getAid(), a.getToNickname(), a.getCreateDate(), a.getContent(), a.getUser().getTeam().getTeamid());

			if(a.getFlag() == 1) {
				teamalarm.add(ma);
			} else {
				snsalarm.add(ma);
			}
		}

		result.status = true;
		result.data = "success";
		result.teamalarm = teamalarm;
		result.snsalarm = snsalarm;
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 알람 삭제
	@PostMapping("/alarm/delete")
	@ApiOperation(value = "알람 삭제", notes = "알람 삭제 구현")
	public void delete(@Valid @RequestParam int aid) throws Exception {
		Optional<Alarm> optionalAlarm = alarmservice.findById(aid);
		Alarm alarm = optionalAlarm.get();

		alarmservice.delete(alarm);
	}
}
