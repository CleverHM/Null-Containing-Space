package com.ssafy.pjt1.controller;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.CustomMailSender;
import com.ssafy.pjt1.dao.UserDao;
import com.ssafy.pjt1.dto.Auth;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.service.AuthService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@CrossOrigin(origins = "*")

@RestController
public class AuthController {
	
	@Autowired
	private CustomMailSender customMailSender;
	@Autowired
	private AuthService authservice;
	@Autowired
	private UserDao userdao;
	
	private String num;
	
	@PostMapping("/auth/loginMailSend")
	@ApiOperation(value = "회원가입시 인증 메일 전송", notes = "회원가입시 인증 메일 전송 기능 구현")
	public Object loginMailSend(@Valid @RequestBody String email) throws MessagingException {
		// 이메일 중복 체크
		Optional<User> user = userdao.findUserByEmail(email);
		// User u = user.get();
		// System.out.println(u.getEmail());
		if (user.isPresent()) {
			System.out.println("이메일 중복");
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "email duplicate!!";
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		} else {
			// 이메일 보내기
			System.out.println(email);
			System.out.println(email.charAt(0));
			customMailSender.SingUpSendMail(email);

			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

	@GetMapping("/auth/confirm/{email}/{auth}")
	@ApiOperation(value = "회원가입시 메일인증", notes = "회원가입시 메일인증 기능 구현")
	public Object loginMailConfirm(@PathVariable String email, @PathVariable String auth) {
		// 확인 하기
		System.out.println(email);
		System.out.println(auth);
a
		Optional<Auth> flag = authservice.findone(email);

		flag.ifPresent(selectUser -> {
			num = selectUser.getAuth_number();
		});

		if (flag.isPresent()) {
			if (num.equals(auth)) {
				final BasicResponse result = new BasicResponse();
				result.status = true;
				result.data = "success";
				System.out.println(num);
				return new ResponseEntity<>(result, HttpStatus.OK);
			} else {
				System.out.println("실패");
				final BasicResponse result = new BasicResponse();
				result.status = true;
				result.data = "인증번호가 다릅니다";
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} else {
			System.out.println("인증을 안받았습니다");
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "인증번호가 없습니다";
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/auth/passwordUpdateMailSend")
	@ApiOperation(value = "비밀번호 변경시 인증 메일 전송", notes = "비밀번호 변경시 인증 메일 전송 기능 구현")
	public Object passwordUpdateMailSend(@Valid @RequestBody String email) throws MessagingException {
		
		// 이메일 중복 체크
		Optional<User> user = userdao.findUserByEmail(email);
		// User u = user.get();
		// System.out.println(u.getEmail());
		
		
		if (!user.isPresent()) {
			System.out.println("이메일이 없습니다.");
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "없는 이메일입니다.";
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		} else {
			// 이메일 보내기
			System.out.println(email);
			System.out.println(email.charAt(0));
			customMailSender.passwordUpdateSendMail(email);

			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

}
