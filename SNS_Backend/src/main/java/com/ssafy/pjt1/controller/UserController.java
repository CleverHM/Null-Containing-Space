package com.ssafy.pjt1.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.pjt1.dto.Ability;
import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.Profile;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.AbilityRequest;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.model.LoginRequest;
import com.ssafy.pjt1.model.SignupRequest;
import com.ssafy.pjt1.service.JwtService;
import com.ssafy.pjt1.service.MatchingService;
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

	@Autowired
	MatchingService matchingservice;

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

		User user2 = userservice.signUp(user1);
		System.out.println(user2.getNickname() + " " + user2.getPassword() + " " + user2.getEmail());
		System.out.println("성공");
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/account/modify")
	@ApiOperation(value = "회원 수정", notes = "회원 수정 기능 구현")
	public Object update(@Valid @RequestParam MultipartFile profile, String email, String nickname, String blog, String git, String intro,
			String password) throws Exception {
		// 프로필 사진 업로드 시작!
		Profile img = new Profile();

		String sourceFileName = profile.getOriginalFilename();
		System.out.println(sourceFileName);
		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
		File destinationFile;
		String destinationFileName;
		String fileUrl = "C:/s03p12d105/SNS_Backend/src/main/resources/static/images";

		do {
			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
			destinationFile = new File(fileUrl + destinationFileName);
		} while (destinationFile.exists());

		destinationFile.getParentFile().mkdirs();
		profile.transferTo(destinationFile);

		img.setFilename(destinationFileName);
		img.setFileOriname(sourceFileName);
		img.setFileurl(fileUrl);

		// 회원 수정 시작!
		System.out.println(email);
		Optional<User> legacyUser = userservice.findone(email);

		User originUser = legacyUser.get();

		originUser.setNickname(nickname);
		originUser.setBlogaddr(blog);
		originUser.setGitaddr(git);
		originUser.setIntro(intro);
		originUser.setPassword(password);
		// User : Profile 정보 이어주기
		originUser.setProfile(img);
		userservice.signUp(originUser);
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
	public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody LoginRequest request,
			HttpServletResponse res) {

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

	@PostMapping("/account/ability")
	@ApiOperation(value = "개인 능력치 입력", notes = "개인 능력치 입력 구현")
	public void getAbility(@Valid @RequestParam String email, @RequestBody AbilityRequest request) {
		List<Integer> list = new ArrayList<>();
		list = request.getAbility();

		Optional<User> u = userservice.findone(email);
		User user = u.get();

		Ability abt = new Ability(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5),
				list.get(6), list.get(7), list.get(8), list.get(9), list.get(10), list.get(11), list.get(12),
				list.get(13), list.get(14));
		user.setAbility(abt);

		userservice.signUp(user);
	}

	@PostMapping("/account/matching")
	@ApiOperation(value = "팀원 추천", notes = "매칭 알고리즘을 구현")
	public Object matchingAlgo(@Valid @RequestBody AbilityRequest request) {
//		Optional<User> u = userservice.findone(email);

//		matchingservice.match(request.getBack(), request.getFront(),request.getDatabase(),request.getFrame(),request.getAlgo());

		ResponseEntity response = null;

		return response;
	}

}