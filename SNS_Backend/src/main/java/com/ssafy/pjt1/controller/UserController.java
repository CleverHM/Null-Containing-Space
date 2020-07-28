package com.ssafy.pjt1.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.pjt1.CustomMailSender;
import com.ssafy.pjt1.dao.PostDao;
import com.ssafy.pjt1.dao.TagDao;
import com.ssafy.pjt1.dao.UserDao;
import com.ssafy.pjt1.dto.Auth;
import com.ssafy.pjt1.dto.Files;
import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.Tag;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.model.LoginRequest;
import com.ssafy.pjt1.model.SignupRequest;
import com.ssafy.pjt1.service.AuthService;
import com.ssafy.pjt1.service.FilesService;
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
	private CustomMailSender customMailSender;
	@Autowired
	private UserService userservice;
	@Autowired
	private AuthService authservice;
	@Autowired
	private UserDao userdao;
	@Autowired
	private TagDao tagdao;
	@Autowired
	private PostDao postdao;
	@Autowired
	private FilesService filesservice;
	@Autowired
	private JwtService jwtservice;
	
	private static final Logger log  = LoggerFactory.getLogger(JwtService.class);
	private String num;

	@PostMapping("/account/loginMailSend")
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

	@PostMapping("/account/loginMailConfirm")
	@ApiOperation(value = "회원가입시 메일인증", notes = "회원가입시 메일인증 기능 구현")
	public Object loginMailConfirm(@Valid @RequestBody Auth auth) {
		// 확인 하기
		System.out.println(auth.getAuth_email());
		System.out.println(auth.getAuth_number());

		Optional<Auth> flag = authservice.findone(auth.getAuth_email());

		flag.ifPresent(selectUser -> {
			num = selectUser.getAuth_number();
		});

		if (flag.isPresent()) {
			if (num.equals(auth.getAuth_number())) {
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

	@PostMapping("/account/passwordUpdateMailSend")
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

	@PostMapping("/account/passwordUpdateMailConfirm")
	@ApiOperation(value = "비밀번호 변경시 메일인증", notes = "비밀번호 변경시 메일인증 기능 구현")
	public Object passwordUpdateMailConfirm(@Valid @RequestBody Auth auth) {
		// 확인 하기
		System.out.println(auth.getAuth_email());
		System.out.println(auth.getAuth_number());

		Optional<Auth> flag = authservice.findone(auth.getAuth_email());

		flag.ifPresent(selectUser -> {
			num = selectUser.getAuth_number();
		});

		if (flag.isPresent()) {
			if (num.equals(auth.getAuth_number())) {
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

// Create
	@PostMapping("/account/signup")
	@ApiOperation(value = "가입하기", notes = "가입하기 기능을 구현")

	public Object signup(@Valid @RequestBody SignupRequest request) {

		User user1 = new User(request.getNickname(), request.getPassword(), request.getEmail(), request.getName(),
				request.getTel(), request.getAge(), request.isGender());
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

	@PostMapping("/account/follow")
	@ApiOperation(value = "유저 팔로우", notes = "사용자간 팔로우 기능을 구현")
	public void userFollow(@Valid @RequestParam String From, @Valid @RequestParam String To) {

		Optional<User> master = userservice.findone(From);
		Optional<User> slave = userservice.findone(To);

		User u1 = master.get();
		User u2 = slave.get();

		u1.getFollowing().add(u2);
		u2.getFollowers().add(u1);

		userservice.signUp(u1);
	}

	@GetMapping("/account/follow/list")
	@ApiOperation(value = "팔로우리스트", notes = "팔로워 리스트, 팔로잉 리스트 보여주기")
	public void userFollowList(@Valid @RequestParam String email) {

		// 뷰에서 사용자의 이메일을 던져주면 그에 해당하는 팔로워들과 팔로우한 사람들을 보여줌.
		Optional<User> temp = userservice.findone(email);
		User u1 = temp.get();
		Set<User> followers = u1.getFollowers();
		Set<User> followings = u1.getFollowing();

		System.out.println("팔로워");
		for (User u : followers)
			System.out.print(u.getEmail() + ", ");

		System.out.println();
		System.out.println("------------------------------");

		System.out.println("팔로잉");
		for (User u : followings)
			System.out.print(u.getEmail() + ", ");

	}

	@PostMapping("/account/tagfollow")
	@ApiOperation(value = "태그", notes = "사용자가 태그를 팔로우하는기능 ")
	public void tagFollow(@Valid @RequestParam String email, @Valid @RequestParam String tagname) {
		Optional<Tag> optionalTag = tagdao.findTagByName(tagname);

		// 태그가 테이블에 존재하지 않는 경우.
		if (!optionalTag.isPresent()) {
			Tag t = new Tag(tagname);
			tagdao.save(t);

			Optional<User> optionalUser = userdao.findUserByEmail(email);
			User u = optionalUser.get();
			u.getTags().add(t);
			t.getUsers().add(u);
			userdao.save(u);

		}
		// 태그가 테이블에 존재하는 경우.
		else {

			Tag t = optionalTag.get();

			System.out.println("태그 있음");

			Optional<User> optionalUser = userdao.findUserByEmail(email);
			User u = optionalUser.get();

			// 태그가 테이블에 존재하나, 유저가 팔로우 하지않은 태그일 경우.
			if (!u.getTags().contains(t)) {
				System.out.println("eee");
				u.getTags().add(t);
				t.getUsers().add(u);
				userdao.save(u);
			}
		}

	}
	
	// 게시물 작성할때 같이 파일이 넘어옴.	
	// 1. 이미지도 저장하고
	// 2. 게시물도 저장하고

	@PostMapping("/account/posting")
	@ApiOperation(value = "유저 게시물작성", notes = "게시물 작성 기능을 구현.")
	public void userPost(@Valid @RequestParam MultipartFile files, String email, String title, String content, String[] hashtags) throws Exception {
		
		// 파일 업로드 시작!
		System.out.println("게시물 작성");
		
		Files img = new Files();
		
		String sourceFileName = files.getOriginalFilename();
		System.out.println(sourceFileName);
		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
		File destinationFile;
		String destinationFileName;
		String fileUrl = "C:/s03p12d105/SNS_Backend/src/main/resources/static/images";
		
		do {
			destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
			destinationFile = new File(fileUrl + destinationFileName);
		}while(destinationFile.exists());
		
		destinationFile.getParentFile().mkdirs();
		files.transferTo(destinationFile);
		
		img.setFilename(destinationFileName);
		img.setFileOriname(sourceFileName);
		img.setFileurl(fileUrl);
		
		
		// 게시물 업로드 시작!	
		Post post = new Post();
		Optional<User> u = userservice.findone(email);
		User pUser = u.get();
		
		// Post : User 정보이어주기.
		post.setUser(pUser);
		post.setTitle(title);
		post.setContent(content);
		// Post : Files 정보이어주기
		post.setImg(img);
		
		
		for(int i=0; i<hashtags.length; i++) {
			Optional<Tag> optionalTag = tagdao.findTagByName(hashtags[i]);

			// 태그가 테이블에 존재하지 않는 경우.
			if (!optionalTag.isPresent()) {
				Tag t = new Tag(hashtags[i]);
				tagdao.save(t);
				// Post : Tag 정보 이어주기.
				post.getTags().add(t);
			}
			
			// 태그가 테이블에 존재하는 경우.
			else {
				Tag t = optionalTag.get();
				System.out.println("태그 있음");
				// Post : Tag 정보 이어주기.
				post.getTags().add(t);
			}	
		}
				
		postdao.save(post);
		System.out.println(post.getContent());
		System.out.println(post.getImg().getFid());
		System.out.println(post.getTitle());
		System.out.println(post.getUser().getUid());
		
	}
}