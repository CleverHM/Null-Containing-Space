package com.ssafy.pjt1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.pjt1.dto.TagFollow;


public interface TagFollowDao extends JpaRepository<TagFollow, Integer> {
	@Transactional
	@Query(value = "select * from tagfollow where user_id=:userId and tag_id=:tagId", nativeQuery=true)
	public TagFollow findRelation(@Param("userId") int userId, @Param("tagId") int tagId);
	
	@Transactional
	@Modifying
	@Query(value = "select * from tagfollow where user_id=:userId", nativeQuery=true)
	int deleteRelation(@Param("userId") int userId);
	
}
