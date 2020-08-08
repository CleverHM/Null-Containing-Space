package com.ssafy.pjt1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.pjt1.dto.Alarm;

public interface AlarmDao extends JpaRepository<Alarm, Integer>{
}
