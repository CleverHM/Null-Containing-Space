package com.ssafy.pjt1.dao;

<<<<<<< HEAD
import java.util.Optional;

=======
>>>>>>> backend
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.pjt1.dto.UserFollow;

public interface UserFollowDao extends JpaRepository<UserFollow, Integer> {
	
	@Transactional
	@Modifying
	@Query(value = "delete from userfollow where from_id=:from and to_id=:to", nativeQuery=true)
	void deleteRelation(@Param("from") int from, @Param("to") int to);
	
	@Transactional
	@Modifying
	@Query(value = "delete from userfollow where from_id=:from", nativeQuery=true)
	void deleteByFrom(@Param("from") int from);
	
	@Transactional
	@Modifying
	@Query(value = "delete from userfollow where to_id=:to", nativeQuery=true)
	void deleteByTo(@Param("to") int to);
	
}
