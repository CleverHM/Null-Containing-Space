package com.ssafy.pjt1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.dao.TagDao;
import com.ssafy.pjt1.dao.UserDao;
import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.PostLike;
import com.ssafy.pjt1.dto.Tag;
import com.ssafy.pjt1.dto.TagFollow;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.dto.UserFollow;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.model.FeedData;
import com.ssafy.pjt1.model.FollowList;
import com.ssafy.pjt1.model.MyAlarm;
import com.ssafy.pjt1.service.FollowService;
import com.ssafy.pjt1.service.UserService;

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
	public Object userFollow(@Valid @RequestParam String From, @Valid @RequestParam String To) {

		int followFlag = 0;

		// from의 팔로우 리스트 확인
		Optional<User> U1 = userservice.findtwo(From);
		Optional<User> U2 = userservice.findtwo(To);
		User u1 = U1.get();
		User u2 = U2.get();

		Set<UserFollow> followings = u1.getFollowings();

		// 먼저 post의 좋아요 개수를 받는다
		for (UserFollow pl : followings) {
			// System.out.println(pl.getFrom().getEmail());
			System.out.println(pl.getTo().getEmail());
			if (pl.getTo().getNickname().equals(To)) {
				followFlag = 1;
				break;
			}
		}

		int followerCnt = 0;
		int followingCnt = 0;
		followerCnt = followservice.followerCount(u2);
		followingCnt = followservice.followingCount(u2);

		System.out.println(followFlag);
		System.out.println("팔로워 : " + followerCnt + "  팔로잉 : " + followingCnt);
		// 이미 팔로우한 사람이 없는 경우.
		if (followFlag == 0) {
//			// u1이 u2 팔로우하는거임.

			UserFollow userfollow = new UserFollow();
			userfollow.setFrom(u1);
			userfollow.setTo(u2);
			followservice.followUser(userfollow);

			// u2의 팔로워 팔로잉 개수
			followFlag = 1;
			followerCnt = followerCnt + 1;
			System.out.println("팔로워 : " + followerCnt + "  팔로잉 : " + followingCnt);
		} else {
			// u1이 u2 언팔로우하는거임.

			followservice.unfollowUser(u1.getUid(), u2.getUid());

			// u2의 팔로워 팔로잉 개수
			followFlag = 0;
			followerCnt = followerCnt - 1;
			System.out.println("팔로워 : " + followerCnt + "  팔로잉 : " + followingCnt);
		}

		Map<String, Integer> resultMap = new HashMap<>();
		resultMap.put("flag", followFlag);
		resultMap.put("followerCnt", followerCnt);
		resultMap.put("followingCnt", followingCnt);

		return new ResponseEntity<>(resultMap, HttpStatus.ACCEPTED);
	}

	@PostMapping("/unfollow/user")
	@ApiOperation(value = "유저 언팔로우", notes = "사용자간 언팔로우 기능을 구현")
	public void userUnfollow(@Valid @RequestParam String From, @Valid @RequestParam String To) {

		// u1이 u2 언팔로우하는거임.
		Optional<User> U1 = userservice.findtwo(From);
		Optional<User> U2 = userservice.findtwo(To);

		User u1 = U1.get();
		User u2 = U2.get();

		followservice.unfollowUser(u1.getUid(), u2.getUid());

		// u2의 팔로워 팔로잉 개수
		int cnt1 = followservice.followerCount(u2);
		int cnt2 = followservice.followingCount(u2);

		System.out.println("팔로워 : " + cnt1 + "  팔로잉 : " + cnt2);
	}

	@PostMapping("/follow/user/list")
	@ApiOperation(value = "팔로우리스트", notes = "팔로워 리스트, 팔로잉 리스트 보여주기")
	public List<FollowList> userFollowList(@Valid @RequestParam String nickname, int flag, int pagenum) throws IOException {

		// 뷰에서 사용자의 이메일을 던져주면 그에 해당하는 팔로워들과 팔로우한 사람들을 보여줌.
		// flag : 1 -> 팔로잉 / 2 -> 팔로워

		List<FollowList> list = new LinkedList<FollowList>();

		Optional<User> U1 = userservice.findtwo(nickname);
		User u1 = U1.get();

		if (flag == 1) {
			System.out.println("팔로잉");

			Set<UserFollow> followings = u1.getFollowings();

			for (UserFollow uf : followings) {
				System.out.println(uf.getTo().getEmail());

				byte[] reportBytes = null;
				File result = new File(uf.getTo().getProfile().getFileurl() + uf.getTo().getProfile().getFilename());

				if (result.exists()) {
					System.out.println("있음");
					InputStream inputStream = new FileInputStream(
							uf.getTo().getProfile().getFileurl() + uf.getTo().getProfile().getFilename());

					byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

					list.add(new FollowList(out, uf.getTo().getNickname()));
					// respEntity = new ResponseEntity(out, responseHeaders, HttpStatus.OK));
				} else {
					list.add(new FollowList(reportBytes, uf.getTo().getNickname()));
					System.out.println("없는 파일");
					// respEntity = new ResponseEntity ("File Not Found", HttpStatus.OK);
				}
			}

		} else {
			System.out.println("-----------------------------------------");
			Set<UserFollow> followers = u1.getFollowers();

			System.out.println("팔로워");

			for (UserFollow uf : followers) {
				System.out.println(uf.getFrom().getEmail());

				byte[] reportBytes = null;
				File result = new File(
						uf.getFrom().getProfile().getFileurl() + uf.getFrom().getProfile().getFilename());

				if (result.exists()) {
					System.out.println("있음");
					InputStream inputStream = new FileInputStream(
							uf.getFrom().getProfile().getFileurl() + uf.getFrom().getProfile().getFilename());

					byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

					list.add(new FollowList(out, uf.getFrom().getNickname()));
					// respEntity = new ResponseEntity(out, responseHeaders, HttpStatus.OK));
				} else {
					list.add(new FollowList(reportBytes, uf.getFrom().getNickname()));
					System.out.println("없는 파일");
					// respEntity = new ResponseEntity ("File Not Found", HttpStatus.OK);
				}
			}

		}

		// 알람 10개씩 보내기
		// page 만큼 자르기
		List<FollowList> listPage = new LinkedList<FollowList>();

		int tcnt = 10;
		int tmin = pagenum * tcnt - tcnt;
		int tmax = pagenum * tcnt;

		if (tmin < list.size()) {
			for (int i = tmin; i < tmax; i++) {
				if (i == list.size()) {
					break;
				}
				listPage.add(list.get(i));
			}
		}

		return listPage;
	}

	@PostMapping("/unfollow/tag")
	@ApiOperation(value = "태그", notes = "사용자가 태그를 언팔로우하는기능 ")
	public void tagUnFollow(@Valid @RequestParam String nickname, @Valid @RequestParam String tagname) {
		// u1이 u2 언팔로우하는거임.
		Optional<User> U1 = userservice.findtwo(nickname);
		Optional<Tag> T1 = tagdao.findTagByName(tagname);

		User u1 = U1.get();
		Tag t2 = T1.get();

		followservice.unfollowTag(u1.getUid(), t2.getTid());
	}

	@PostMapping("/follow/tag")
	@ApiOperation(value = "태그", notes = "사용자가 태그를 팔로우하는기능 ")
	public Object tagFollow(@Valid @RequestParam String nickname, @Valid @RequestParam String tagname) {
		Optional<User> U1 = userservice.findtwo(nickname);
		User user = U1.get();
		Optional<Tag> optionalTag = tagdao.findTagByName(tagname);

		// 태그가 테이블에 존재하지 않는 경우.
		if (!optionalTag.isPresent()) {

			// 나에게 있는지 판별

			Set<TagFollow> tagfollow = user.getTagfollows();

			int flag = 0;

			for (TagFollow tf : tagfollow) {
				if (tf.getTag().getName().equals(tagname)) {
					System.out.println("태그 있음1");
					flag = 1;
					break;
				}
			}

			// flag == 0 이면 팔로우 안되어있다.
			if (flag == 0) {
				Tag t = new Tag(tagname);
				tagdao.save(t);

				Optional<User> optionalUser = userservice.findtwo(nickname);
				User u = optionalUser.get();

				TagFollow tf = new TagFollow();
				tf.setUser(u);
				tf.setTag(t);
				followservice.followTag(tf);
				return true;
			} else {
				// 언팔
				Tag t2 = optionalTag.get();
				followservice.unfollowTag(user.getUid(), t2.getTid());
				return false;
			}
		}
		// 태그가 테이블에 존재하는 경우.
		else {

			Tag t = optionalTag.get();

			System.out.println("태그 있음");

			Optional<User> optionalUser = userservice.findtwo(nickname);
			User u = optionalUser.get();

			// 태그가 테이블에 존재하나, 유저가 팔로우 하지않은 태그일 경우.

			Set<TagFollow> tagfollows = u.getTagfollows();
			boolean isExist = false;

			for (TagFollow tf : tagfollows) {
				System.out.println(tf.getTag().getName());
				// 내가 팔로우한 태그목록에 이미 태그가 존재하는 경우!
				if (tf.getTag().getTid() == t.getTid()) {
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
				return true;
			} else {
				// 언팔
				Tag t2 = optionalTag.get();
				System.out.println(user.getUid() + " " + t2.getTid());
				followservice.unfollowTag(user.getUid(), t2.getTid());
				return false;
			}

		}

	}
}
