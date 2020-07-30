package com.ssafy.pjt1.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.User;

public interface PostDao extends JpaRepository<Post, Integer>{
	@Transactional
    @Modifying
    @Query(value="delete from posttag where post_id=:pid", nativeQuery=true)
    void deletetag(@Param("pid") int pid);
	
	Optional<Post> findPostByPid(int pid);
}
