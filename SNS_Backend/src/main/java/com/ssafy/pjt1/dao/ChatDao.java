package com.ssafy.pjt1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.pjt1.dto.Chat;

public interface ChatDao extends JpaRepository<Chat, Integer>{
	@Transactional
    @Modifying
    @Query(value="delete from chat where team_id=:teamid", nativeQuery=true)
    void deleteChat(@Param("teamid") int teamid);
}
