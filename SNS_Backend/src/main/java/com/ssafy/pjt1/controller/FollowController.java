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
import com.ssafy.pjt1.dto.TagFollow;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.dto.UserFollow;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.service.FollowService;
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
	@Autowired
	private FollowService followservice;
	
	@PostMapping("/follow/user")
	@ApiOperation(value = "유저 팔로우", notes = "사용자간 팔로우 기능을 구현")
	public void userFollow(@Valid @RequestParam String From, @Valid @RequestParam String To) {

		// u1이 u2 팔로우하는거임.
		Optional<User> U1 = userservice.findone(From);
		Optional<User> U2 = userservice.findone(To);

		User u1 = U1.get();
		User u2 = U2.get();

		UserFollow userfollow = new UserFollow();
		userfollow.setFrom(u1);
		userfollow.setTo(u2);
		followservice.followUser(userfollow);
		
		// u2의 팔로워 팔로잉 개수		
		int cnt1 = followservice.followerCount(u2);
		int cnt2 = followservice.followingCount(u2);
		
		System.out.println("팔로워 : " + cnt1 + "  팔로잉 : " + cnt2);
	}
	
	@PostMapping("/unfollow/user")
	@ApiOperation(value = "유저 언팔로우", notes = "사용자간 언팔로우 기능을 구현")
	public void userUnfollow(@Valid @RequestParam String From, @Valid @RequestParam String To) {

		// u1이 u2 언팔로우하는거임.
		Optional<User> U1 = userservice.findone(From);
		Optional<User> U2 = userservice.findone(To);

		User u1 = U1.get();
		User u2 = U2.get();
		
		followservice.unfollowUser(u1.getUid(), u2.getUid());
		
		// u2의 팔로워 팔로잉 개수		
		int cnt1 = followservice.followerCount(u2);
		int cnt2 = followservice.followingCount(u2);
		
		System.out.println("팔로워 : " + cnt1 + "  팔로잉 : " + cnt2);
	}
	

	@GetMapping("/follow/user/list")
	@ApiOperation(value = "팔로우리스트", notes = "팔로워 리스트, 팔로잉 리스트 보여주기")
	public void userFollowList(@Valid @RequestParam String email) {

		// 뷰에서 사용자의 이메일을 던져주면 그에 해당하는 팔로워들과 팔로우한 사람들을 보여줌.
		Optional<User> U1 = userservice.findone(email);
		User u1 = U1.get();
		
		Set<UserFollow> followers = u1.getFollowers();
		
		System.out.println("팔로워");
		
		for(UserFollow uf: followers) {
			System.out.println(uf.getFrom().getEmail());
		}
		
		
		System.out.println("-----------------------------------------");
		
		
		System.out.println("팔로잉");
		
		Set<UserFollow> followings = u1.getFollowings();
		
		for(UserFollow uf: followings) {
			System.out.println(uf.getTo().getEmail());
		}

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
			
			TagFollow tf = new TagFollow();
			tf.setUser(u);
			tf.setTag(t);
			followservice.followTag(tf);
			
		}
		// 태그가 테이블에 존재하는 경우.
		else {

			Tag t = optionalTag.get();

			System.out.println("태그 있음");

			Optional<User> optionalUser = userdao.findUserByEmail(email);
			User u = optionalUser.get();

			// 태그가 테이블에 존재하나, 유저가 팔로우 하지않은 태그일 경우.
			
			Set<TagFollow> tagfollows = u.getTagfollows();
			boolean isExist = false;
			
			for(TagFollow tf : tagfollows) {
				System.out.println(tf.getTag().getName());
				// 내가 팔로우한 태그목록에 이미 태그가 존재하는 경우!
				if(tf.getTag().getTid() == t.getTid()) {
					isExist = true;
					break;
				}
			}
			
			// 내가 팔로우한 태그목록에 태그가 존재하지 않는경우!
			if (isExist == false) {
				TagFollow tf = new TagFollow();
				tf.setUser(u);
				tf.setTag(t);
				followservice.followTag(tf);
			}
		}

	}
}
