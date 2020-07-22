
package com.ssafy.pjt1.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.SignupRequest;

public interface UserDao extends JpaRepository<User, Integer> {
	
    Optional<User> findUserByEmailAndPassword(String email, String password);
    Optional<User> findUserByEmail(String email);
    Optional<User> deleteUserByEmail(String email);
    //Optional<User> save(SignupRequest request);
    //Optional<User> delete(SignupRequest request);
}
