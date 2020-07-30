package com.ssafy.pjt1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.pjt1.dto.PostTag;

public interface PostTagDao extends JpaRepository<PostTag, Integer> {
	@Transactional
    @Modifying
    @Query(value="delete from posttag where post_id=:pid", nativeQuery=true)
    void deleteUser2(@Param("pid") int pid);
}
