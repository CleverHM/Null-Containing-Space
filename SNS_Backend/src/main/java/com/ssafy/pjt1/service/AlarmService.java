package com.ssafy.pjt1.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.pjt1.dto.Alarm;

public interface AlarmService {
	void sendAlarm(Alarm alarm);
	void delete(Alarm alarm);
	List<Alarm> meAlarm(String mynickname);
	Optional<Alarm>  findById(int aid);
}
