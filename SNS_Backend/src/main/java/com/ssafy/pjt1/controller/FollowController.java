package com.ssafy.pjt1.controller;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.dao.TagDao;
import com.ssafy.pjt1.dao.UserDao;
import com.ssafy.pjt1.dto.Tag;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.BasicResponse;
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
public class FollowController {
	@Autowired
	private UserService userservice;
	@Autowired
	private TagDao tagdao;
	@Autowired
	private UserDao userdao;
	
	@PostMapping("/follow/user")
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

	@GetMapping("/follow/user/list")
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

	@PostMapping("/follow/tag")
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
}
