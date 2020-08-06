package com.ssafy.pjt1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.http.HttpHeaders;
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
import com.ssafy.pjt1.dto.TagFollow;
import com.ssafy.pjt1.dto.Team;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.dto.UserFollow;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.model.LoginRequest;
import com.ssafy.pjt1.model.MachingRequest;
import com.ssafy.pjt1.model.MyPageData;
import com.ssafy.pjt1.model.SignupRequest;
import com.ssafy.pjt1.service.FollowService;
import com.ssafy.pjt1.service.JwtService;
import com.ssafy.pjt1.service.MatchingService;
import com.ssafy.pjt1.service.TeamService;
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
	private MatchingService matchingservice;

	@Autowired
	private FollowService followservice;

	@Autowired
	private TeamService teamservice;

	// eamil 중복 체크
	@PostMapping("/account/emailDuplicate")
	@ApiOperation(value = "이메일 중복체크", notes = "이메일 중복체크 기능을 구현")

	public Object emailDuplicate(@Valid @RequestBody String email) {
		Optional<User> optionaluser = userservice.findone(email);

		if (optionaluser.isPresent()) {
			System.out.println("실패");
			final BasicResponse result = new BasicResponse();
			result.status = false;
			result.data = "이멩일이 중복 되었습니다.";
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		} else {
			System.out.println("성공");
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

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

		Optional<Team> t = teamservice.findone(1);

		if (!t.isPresent()) {
			Team team = new Team("default 팀 입니다.", "default", Integer.MAX_VALUE);
			teamservice.join(team);
		}

		Profile img = new Profile();

		String sourceFileName = "standard.PNG";
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
		// profile.transferTo(destinationFile);

		img.setFilename(destinationFileName);
		img.setFileOriname(sourceFileName);
		img.setFileurl(fileUrl);

		List<Integer> list = request.getAbility();

		Ability abt = new Ability(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5),
				list.get(6), list.get(7), list.get(8), list.get(9), list.get(10), list.get(11), list.get(12),
				list.get(13), list.get(14));

		User user1 = new User(request.getNickname(), request.getPassword(), request.getEmail(), request.getName(),
				request.getTel(), request.getAge(), request.isGender(), request.getGitaddr(), request.getBlogaddr(),
				request.getIntro(), abt, img, false, false, 1);

		Optional<Team> t1 = teamservice.findone(1);
		user1.setTeam(t1.get());

		User user2 = userservice.signUp(user1);
		System.out.println(user2.getNickname() + " " + user2.getPassword() + " " + user2.getEmail());
		System.out.println("성공");
		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/account/modifyTrue")
	@ApiOperation(value = "회원 수정", notes = "회원 수정 기능 구현")
	public Object updatetrue(@Valid @RequestParam MultipartFile profile, String email, String nickname, String blog,
			String git, String intro) throws Exception {
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

	@PostMapping("/account/modifyFalse")
	@ApiOperation(value = "회원 수정", notes = "회원 수정 기능 구현")
	public Object updatefalse(@Valid @RequestParam String email, String nickname, String blog, String git, String intro)
			throws Exception {
		// 회원 수정 시작!
		System.out.println(email);
		Optional<User> legacyUser = userservice.findone(email);

		User originUser = legacyUser.get();

		originUser.setNickname(nickname);
		originUser.setBlogaddr(blog);
		originUser.setGitaddr(git);
		originUser.setIntro(intro);
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
	public Object delete(@Valid @RequestParam String nickname) {
		Optional<User> user2 = userservice.findtwo(nickname);

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

	@PostMapping("/account/tokenAuth")
	@ApiOperation(value = "토큰 확인 ", notes = "토큰 확인을 구현")
	public Object login(@Valid @RequestParam String token) {

		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println(jwtservice.isUsable(token));
		if (jwtservice.isUsable(token)) {
			resultMap.put("message", "인증 성공");
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

	@PostMapping("/account/myPage")
	@ApiOperation(value = "프로필 페이지", notes = "프로필 페이지 보여주기 기능을 구현.")
	public MyPageData myPageDetail(@Valid @RequestParam String nickname, String pageNickname)
			throws FileNotFoundException, IOException {
		List<String> tag = new ArrayList<>();
		List<Integer> abt = new ArrayList<>();

		Optional<User> optionalUser = userservice.findtwo(pageNickname);
		User user = optionalUser.get();

		System.out.println(user.getEmail());

		int followercnt = followservice.followerCount(user);
		int followingcnt = followservice.followingCount(user);

		System.out.println(followercnt + ", " + followingcnt);
		boolean me = true;
		boolean follow = true;
		MyPageData mypage = null;
		
		if(nickname.equals(pageNickname)) {
			if (user.getTagfollows().size() != 0) {
				for (TagFollow t : user.getTagfollows()) {
					System.out.print(t.getTag().getName() + " ");
					tag.add(t.getTag().getName());
				}
			}
			abt.add(user.getAbility().getBack_cpp());
			abt.add(user.getAbility().getBack_java());
			abt.add(user.getAbility().getBack_python());
			abt.add(user.getAbility().getBack_php());
			abt.add(user.getAbility().getFront_html());
			abt.add(user.getAbility().getFront_css());
			abt.add(user.getAbility().getFront_javascript());
			abt.add(user.getAbility().getDb_sql());
			abt.add(user.getAbility().getDb_nosql());
			abt.add(user.getAbility().getFrame_spring());
			abt.add(user.getAbility().getFrame_django());
			abt.add(user.getAbility().getFrame_bootstrap());
			abt.add(user.getAbility().getFrame_vue());
			abt.add(user.getAbility().getFrame_react());
			abt.add(user.getAbility().getAlgo());

			// 이미지
			byte[] reportBytes = null;
			File result = new File(user.getProfile().getFileurl() + user.getProfile().getFilename());

			if (result.exists()) {
				System.out.println("있음");
				InputStream inputStream = new FileInputStream(
						user.getProfile().getFileurl() + user.getProfile().getFilename());
				String type = result.toURL().openConnection().guessContentTypeFromName(user.getProfile().getFilename());

				byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.add("content-disposition", "attachment; filename=" + user.getProfile().getFilename());
				responseHeaders.add("Content-Type", type);

				mypage = new MyPageData(user.getNickname(), followercnt, followingcnt, user.getBlogaddr(), user.getGitaddr(),
						user.getIntro(), tag, abt, out, me, follow);
			} else {
				mypage = new MyPageData(user.getNickname(), followercnt, followingcnt, user.getBlogaddr(), user.getGitaddr(),
						user.getIntro(), tag, abt, reportBytes, me, follow);
				System.out.println("프로필 파일 없음");
			}
		} else {
			// 페이지가 내가 아니야
			// 내가 pageNickname 을 팔로우 했는지 판단
			Optional<User> optionalUser1 = userservice.findtwo(nickname);
			User user1 = optionalUser1.get();
			
			Set<UserFollow> uf = user1.getFollowings();
			
			int f = 0;
			
			end : for(UserFollow userfollow : uf) {
				if(userfollow.getTo().getNickname().equals(pageNickname)) {
					f = 1;
					break end;
				}
			}
			
			// 팔로우 하지 않았다.
			if(f == 0) {
				if (user.getTagfollows().size() != 0) {
					for (TagFollow t : user.getTagfollows()) {
						System.out.print(t.getTag().getName() + " ");
						tag.add(t.getTag().getName());
					}
				}
				abt.add(user.getAbility().getBack_cpp());
				abt.add(user.getAbility().getBack_java());
				abt.add(user.getAbility().getBack_python());
				abt.add(user.getAbility().getBack_php());
				abt.add(user.getAbility().getFront_html());
				abt.add(user.getAbility().getFront_css());
				abt.add(user.getAbility().getFront_javascript());
				abt.add(user.getAbility().getDb_sql());
				abt.add(user.getAbility().getDb_nosql());
				abt.add(user.getAbility().getFrame_spring());
				abt.add(user.getAbility().getFrame_django());
				abt.add(user.getAbility().getFrame_bootstrap());
				abt.add(user.getAbility().getFrame_vue());
				abt.add(user.getAbility().getFrame_react());
				abt.add(user.getAbility().getAlgo());

				// 이미지
				byte[] reportBytes = null;
				File result = new File(user.getProfile().getFileurl() + user.getProfile().getFilename());

				if (result.exists()) {
					System.out.println("있음");
					InputStream inputStream = new FileInputStream(
							user.getProfile().getFileurl() + user.getProfile().getFilename());
					String type = result.toURL().openConnection().guessContentTypeFromName(user.getProfile().getFilename());

					byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.add("content-disposition", "attachment; filename=" + user.getProfile().getFilename());
					responseHeaders.add("Content-Type", type);

					mypage = new MyPageData(user.getNickname(), followercnt, followingcnt, user.getBlogaddr(), user.getGitaddr(),
							user.getIntro(), tag, abt, out, false, false);
				} else {
					mypage = new MyPageData(user.getNickname(), followercnt, followingcnt, user.getBlogaddr(), user.getGitaddr(),
							user.getIntro(), tag, abt, reportBytes, false, false);
					System.out.println("프로필 파일 없음");
				}
			}
			// 팔로우함
			else {
				if (user.getTagfollows().size() != 0) {
					for (TagFollow t : user.getTagfollows()) {
						System.out.print(t.getTag().getName() + " ");
						tag.add(t.getTag().getName());
					}
				}
				abt.add(user.getAbility().getBack_cpp());
				abt.add(user.getAbility().getBack_java());
				abt.add(user.getAbility().getBack_python());
				abt.add(user.getAbility().getBack_php());
				abt.add(user.getAbility().getFront_html());
				abt.add(user.getAbility().getFront_css());
				abt.add(user.getAbility().getFront_javascript());
				abt.add(user.getAbility().getDb_sql());
				abt.add(user.getAbility().getDb_nosql());
				abt.add(user.getAbility().getFrame_spring());
				abt.add(user.getAbility().getFrame_django());
				abt.add(user.getAbility().getFrame_bootstrap());
				abt.add(user.getAbility().getFrame_vue());
				abt.add(user.getAbility().getFrame_react());
				abt.add(user.getAbility().getAlgo());

				// 이미지
				byte[] reportBytes = null;
				File result = new File(user.getProfile().getFileurl() + user.getProfile().getFilename());

				if (result.exists()) {
					System.out.println("있음");
					InputStream inputStream = new FileInputStream(
							user.getProfile().getFileurl() + user.getProfile().getFilename());
					String type = result.toURL().openConnection().guessContentTypeFromName(user.getProfile().getFilename());

					byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.add("content-disposition", "attachment; filename=" + user.getProfile().getFilename());
					responseHeaders.add("Content-Type", type);

					mypage = new MyPageData(user.getNickname(), followercnt, followingcnt, user.getBlogaddr(), user.getGitaddr(),
							user.getIntro(), tag, abt, out, false, true);
				} else {
					mypage = new MyPageData(user.getNickname(), followercnt, followingcnt, user.getBlogaddr(), user.getGitaddr(),
							user.getIntro(), tag, abt, reportBytes, false, true);
					System.out.println("프로필 파일 없음");
				}
			}
		}
		

		return mypage;
	}

}