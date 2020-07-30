package com.ssafy.pjt1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.pjt1.dto.PostLike;

public interface PostLikeDao extends JpaRepository<PostLike, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "delete from postlike where user_id=:user and post_id=:post", nativeQuery=true)
	void deleteRelation(@Param("user") int user, @Param("post") int post);
}
