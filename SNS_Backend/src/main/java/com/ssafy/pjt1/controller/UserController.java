package com.ssafy.pjt1.controller;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.CustomMailSender;
import com.ssafy.pjt1.dto.Auth;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.model.LoginRequest;
import com.ssafy.pjt1.model.SignupRequest;
import com.ssafy.pjt1.service.AuthService;
import com.ssafy.pjt1.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "http://localhost:3000" })

@RestController
public class UserController {

	@Autowired
	CustomMailSender customMailSender;

	@Autowired
	UserService userservice;

	@Autowired
	AuthService authservice;
	
//	@Autowired
//	FollowDao followdao;

	private String num;

	@PostMapping("/account/loginMailSend")
	@ApiOperation(value = "회원가입시 인증 메일 전송", notes = "회원가입시 인증 메일 전송 기능 구현")
	public void loginMailSend(@Valid @RequestBody String email) throws MessagingException {
		// 이메일 보내기
		System.out.println(email);
		System.out.println(email.charAt(0));
		customMailSender.sendMail(email);
	}

	@PostMapping("/account/loginMailConfirm")
	@ApiOperation(value = "회원가입시 메일인증", notes = "회원가입시 메일인증 기능 구현")

	public Object loginMailConfirm(@Valid @RequestParam Auth auth) {
		// 확인 하기
		System.out.println(auth.getAuth_email());
		System.out.println(auth.getAuth_number());


		Optional<Auth> flag = authservice.findone(auth.getAuth_email());


		flag.ifPresent(selectUser -> {
			num = selectUser.getAuth_number();
		});

		if (flag != null) {
			if (num.equals(auth.getAuth_number())) {
				final BasicResponse result = new BasicResponse();
				result.status = true;
				result.data = "success";
				System.out.println(num);
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				System.out.println("실패");
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} else {
			System.out.println("실패");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	// Create
	@PostMapping("/account/signup")
	@ApiOperation(value = "가입하기", notes = "가입하기 기능을 구현")

	public Object signup(@Valid @RequestBody SignupRequest request) {

		User user1 = new User(request.getNickname(), request.getPassword(), request.getEmail());
		User user2 = userservice.signUp(user1);

		if (user2 == null) {
			System.out.println("실패");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			System.out.println(user2.getNickname() + " " + user2.getPassword() + " " + user2.getEmail());
			System.out.println("성공");
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";

			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}

	@PostMapping("/account/modify")
	@ApiOperation(value = "회원 수정", notes = "회원 수정 기능 구현")
	public Object update(@Valid @RequestParam String email, @RequestBody User user) {

		Optional<User> legacyUser = userservice.findone(email);

		legacyUser.ifPresent(selectUser -> {
			selectUser.setNickname(user.getNickname());
			selectUser.setPassword(user.getPassword());
			userservice.signUp(selectUser);
		});

		if (legacyUser.isPresent()) {
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			System.out.println("실패");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/account/delete")
	@ApiOperation(value = "회원  삭제", notes = "회원 삭제 기능 구현")
	public Object delete(@Valid @RequestBody SignupRequest request) {
		Optional<User> user2 = userservice.findone(request.getEmail());

		System.out.println(user2.toString());
		ResponseEntity response = null;

		if (user2.isPresent()) {
			// userservice.delete(user2);
			user2.ifPresent(selectUser -> {
				userservice.delete(selectUser);
			});
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			System.out.println("성공");
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			System.out.println("실패");
		}

		return response;
	}

	@PostMapping("/account/login")
	@ApiOperation(value = "로그인 ", notes = "로그인 기능을 구현")
	public Object login(@Valid @RequestBody LoginRequest request) {

		System.out.println("email: " + request.getEmail());
		System.out.println("pass: " + request.getPassword());

		Optional<User> userOpt = userservice.login(request.getEmail(), request.getPassword());

		ResponseEntity response = null;

		if (userOpt.isPresent()) {
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
			System.out.println("성공");
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			System.out.println("실패");
		}

		return response;
	}

	@PostMapping("/account/follow")
	@ApiOperation(value = "팔로우 ", notes = "팔로우 기능을 구현")
	public void follow(@Valid @RequestParam String email) {

		User master = new User("hm", "gksrnr7729", "conquerer1209@gmail.com");
		User slave = new User("hyun", "gksrnr7729", email);
		
		master.getFollowing().add(slave);
		
		userservice.signUp(master);
		
	}
}