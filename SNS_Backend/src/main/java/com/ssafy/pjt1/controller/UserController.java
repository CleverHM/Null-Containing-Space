package com.ssafy.pjt1.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
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

import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.model.LoginRequest;
import com.ssafy.pjt1.model.SignupRequest;
import com.ssafy.pjt1.service.JwtService;
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
	private UserService userservice;
	
	@Autowired
	private JwtService jwtservice;

// 중복 체크
	@PostMapping("/account/nickNameDuplicate")
	@ApiOperation(value = "닉네임 중복체크", notes = "닉네임 중복체크 기능을 구현")

	public Object nickNameDuplicate(@Valid @RequestBody String nickname) {
		Optional<User> optionaluser = userservice.duplNick(nickname);
		
		if (optionaluser.isPresent()) {
			System.out.println("실패");
			final BasicResponse result = new BasicResponse();
			result.status = false;
			result.data = "닉네임이 중복 되었습니다.";
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		} else {
			System.out.println("성공");
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
// Create
	@PostMapping("/account/signup")
	@ApiOperation(value = "가입하기", notes = "가입하기 기능을 구현")

	public Object signup(@Valid @RequestBody SignupRequest request) {

		User user1 = new User(request.getNickname(), request.getPassword(), request.getEmail(), request.getName(),
				request.getTel(), request.getAge(), request.isGender());
		
		Optional<User> optionaluser = userservice.duplNick(request.getNickname());
		
		System.out.println(optionaluser.get().getNickname());
		
		if (optionaluser.isPresent()) {
			System.out.println("실패");
			return new ResponseEntity<>("닉네임이 중복 되었습니다.", HttpStatus.NOT_FOUND);
		} else {
			User user2 = userservice.signUp(user1);
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

		User originUser = legacyUser.get();
		
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
			User select = user2.get();
		
			Set<Post> posts = select.getPosts();
			
	        userservice.delete(select, posts);
	        
	        System.out.println("22222222222");
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
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest request, HttpServletResponse res) {
        
        System.out.println("email: " + request.getEmail());
        System.out.println("pass: " + request.getPassword());
        
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        Optional<User> userOpt = userservice.login(request.getEmail(), request.getPassword());


        if (userOpt.isPresent()) {
            User loginUser = userOpt.get();
            String token = jwtservice.create(loginUser.getEmail(), loginUser.getNickname());
            res.setHeader("jwt-auth-token", token);
            
            resultMap.put("token", token);
            resultMap.put("status", true);
            resultMap.put("email", loginUser.getEmail());
            resultMap.put("nickname", loginUser.getNickname());
            status = HttpStatus.ACCEPTED;
            
            System.out.println("로그인 성공");
        } else {
            resultMap.put("message", "로그인 실패");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
	
	@PostMapping("/account/findPasswordModify")
	@ApiOperation(value = "비밀번호 찾기(새로운 비밀 번호 업데이트)", notes = "비밀번호 찾기(새로운 비밀 번호 업데이트) 기능을 구현.")
	public Object token(@Valid @RequestParam String email, String NewPassword) {

	    Optional<User> optionalUser = userservice.findone(email);

	    if (optionalUser.isPresent()) {
	        User user = optionalUser.get();
	        user.setPassword(NewPassword);
	        userservice.signUp(user);
	        final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	       
	        return new ResponseEntity<>(result, HttpStatus.OK);
	        
	    } else {
	        final BasicResponse result = new BasicResponse();
	        result.status = false;
	        result.data = "fail";
	        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
	    }
	}

}