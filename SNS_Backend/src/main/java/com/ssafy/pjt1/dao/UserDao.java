
package com.ssafy.pjt1.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.pjt1.dto.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
    Optional<User> findUserByEmailAndPassword(String email, String password);
    Optional<User> findUserByEmail(String email);
    Optional<User> deleteUserByEmail(String email);
    //Optional<User> save(SignupRequest request);
    //Optional<User> delete(SignupRequest request);
    
    @Transactional
    @Modifying
    @Query(value="delete from userfollow where from_id=:uid", nativeQuery=true)
    void deletefollow1(@Param("uid") int uid);

    @Transactional
    @Modifying
    @Query(value="delete from userfollow where to_id=:uid", nativeQuery=true)
    void deletefollow2(@Param("uid") int uid);

    @Transactional
    @Modifying
    @Query(value="delete from tagfollow where user_id=:uid", nativeQuery=true)
    void deletetag(@Param("uid") int uid);
    
    @Transactional
    @Modifying
    @Query(value="delete from posttag where post_id=:pid", nativeQuery=true)
    void deleteposttag(@Param("pid") int pid);
}
