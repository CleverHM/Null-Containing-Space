package com.ssafy.pjt1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt1.dao.AlarmDao;
import com.ssafy.pjt1.dto.Alarm;

@Service
public class AlarmServiceImpl implements AlarmService{
	
	@Autowired
	AlarmDao alarmdao;
	
	@Override
	public void sendAlarm(Alarm alarm) {
		// TODO Auto-generated method stub
		alarmdao.save(alarm);
	}

	@Override
	public List<Alarm> meAlarm(String mynickname) {
		// TODO Auto-generated method stub
		return alarmdao.meAlarm(mynickname);
	}
	
}
