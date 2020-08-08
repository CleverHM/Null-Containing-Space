package com.ssafy.pjt1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.pjt1.dto.Alarm;

public interface AlarmDao extends JpaRepository<Alarm, Integer>{
	
	@Transactional
	@Query(value = "select * from alarm where to_nickname=:to_nickname ", nativeQuery=true)
	public List<Alarm> meAlarm(@Param("to_nickname") String mynickname);
	
}
