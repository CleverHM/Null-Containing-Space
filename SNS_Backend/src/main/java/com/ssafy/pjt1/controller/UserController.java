package com.ssafy.pjt1.controller;

import java.util.Date;
import java.util.Optional;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.CustomMailSender;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.model.LoginRequest;
import com.ssafy.pjt1.model.SignupRequest;
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

	@PostMapping("/account/sendMail")
	@ApiOperation(value = "메일 보내기", notes = "메일 보내기 기능 구현")
	public void send(@Valid @RequestParam String email) throws MessagingException {

		// 이메일 보내기
		customMailSender.sendMail(email);

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
	public Object login(@Valid @RequestBody LoginRequest request){

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

	@PostMapping("/account/signup")
	@ApiOperation(value = "가입하기", notes = "가입하기 기능을 구현")
	public Object signup(@Valid @RequestBody SignupRequest request) {

		User user1 = new User(request.getEmail(), request.getPassword(), request.getNickname());
		User user2 = userservice.signUp(user1);

		System.out.println(user2.getEmail() + " " + user2.getCreateDate() + " " + user2.getPassword());

		if (user2 == null) {
			System.out.println("실패");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			System.out.println("성공");
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";

			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}
}