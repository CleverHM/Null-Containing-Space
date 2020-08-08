package com.ssafy.pjt1.service;

import java.util.List;

import com.ssafy.pjt1.dto.Alarm;

public interface AlarmService {
	void sendAlarm(Alarm alarm);
	
	List<Alarm> meAlarm(String mynickname);
}
