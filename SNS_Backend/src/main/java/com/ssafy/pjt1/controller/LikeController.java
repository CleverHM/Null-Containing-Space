package com.ssafy.pjt1.controller;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.dao.PostLikeDao;
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
	public void userLikePost(@Valid @RequestParam String user, @Valid @RequestParam int postid) {
		
		// 좋아요 버튼 or 좋아요 취소 버튼 눌린 게시물을 들고옴.
		Optional<Post> temp = postservice.findone(postid);
		Post post = temp.get();
		
		post.getPostlikes().

		// 유저 u가 게시물 p를 좋아요 하는거임.
		Optional<User> U = userservice.findone(user);
		Optional<Post> P = postservice.findone(postid);

		User u = U.get();
		Post p = P.get();

		PostLike postlike = new PostLike();
		postlike.setUser(u);
		postlike.setPost(p);
		likeservice.userLikePost(postlike);
		
		// 게시물에 해당하는 좋아요 개수
		int count = likeservice.likeCount(p);
		
		System.out.println(count);
	}
	
	@PostMapping("/unlike/post")
	@ApiOperation(value = "게시물 좋아요 취소", notes = "사용자가 게시물을 좋아요 취소하는 기능을 구현")
	public void userUnfollow(@Valid @RequestParam String user, @Valid @RequestParam int postid) {

		// 유저 u가 게시물 p를 좋아요 취소하는거임.
		Optional<User> U = userservice.findone(user);
		Optional<Post> P = postservice.findone(postid);

		User u = U.get();
		Post p = P.get();

		likeservice.userUnlikePost(u.getUid(), p.getPid());
		
		// 게시물에 해당하는 좋아요 개수
		int count = likeservice.likeCount(p);
		
		System.out.println(count);
	}
}
