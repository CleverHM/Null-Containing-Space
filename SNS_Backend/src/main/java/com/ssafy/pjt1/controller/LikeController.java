package com.ssafy.pjt1.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.PostLike;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.service.LikeService;
import com.ssafy.pjt1.service.PostService;
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
public class LikeController {
	@Autowired
	private UserService userservice;
	@Autowired
	private PostService postservice;
	@Autowired
	private LikeService likeservice;
	
	@PostMapping("/like/post")
	@ApiOperation(value = "게시물 좋아요", notes = "사용자가 게시물을 좋아요 하는 기능을 구현")
	public Object userLikePost(@Valid @RequestParam String email, @Valid @RequestParam int postid) {
		int likeFlag = 0;
		int count = 0;
		
		// 좋아요 버튼 or 좋아요 취소 버튼 눌린 게시물을 들고옴.
		Optional<User> tempU = userservice.findone(email);
		User user = tempU.get();
		
		Optional<Post> tempP = postservice.findone(postid);
		Post post = tempP.get();
		Set <PostLike> postlikes = post.getPostlikes();
		
		for(PostLike pl : postlikes) {
			// 이미 좋아요한 사람일 경우.
			if(pl.getUser().getUid() == user.getUid()) {
				likeFlag = 1;
				break;
			}
		}
		
		// 이미 좋아요한 사람일 경우.
		if(likeFlag == 1) {
			// 유저 u가 게시물 p를 좋아요 취소하는거임.
			Optional<User> U = userservice.findone(email);
			Optional<Post> P = postservice.findone(postid);

			User u = U.get();
			Post p = P.get();

			likeservice.userUnlikePost(u.getUid(), p.getPid());
			
			// 게시물에 해당하는 좋아요 개수
			count = likeservice.likeCount(p);
			
			System.out.println(count);
		}else {
			// 유저 u가 게시물 p를 좋아요 하는거임.
			Optional<User> U = userservice.findone(email);
			Optional<Post> P = postservice.findone(postid);

			User u = U.get();
			Post p = P.get();

			PostLike postlike = new PostLike();
			postlike.setUser(u);
			postlike.setPost(p);
			likeservice.userLikePost(postlike);
			
			// 게시물에 해당하는 좋아요 개수
			count = likeservice.likeCount(p);
			
			System.out.println(count);	
		}
		
		
		Map<String, Integer> resultMap = new HashMap<>();
		resultMap.put("flag", likeFlag);
		resultMap.put("count", count);
		
		return new ResponseEntity<>(resultMap, HttpStatus.ACCEPTED);

	}
	
//	@PostMapping("/unlike/post")
//	@ApiOperation(value = "게시물 좋아요 취소", notes = "사용자가 게시물을 좋아요 취소하는 기능을 구현")
//	public void userUnfollow(@Valid @RequestParam String user, @Valid @RequestParam int postid) {
//
//		
//	}
}
