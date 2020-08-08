package com.ssafy.pjt1.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.dao.ReplyDao;
import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.Reply;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.service.PostService;
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
public class ReplyController {
	@Autowired
    private UserService userservice;
	@Autowired
	private PostService postservice;
	@Autowired
	private ReplyDao replydao;
	
	@PostMapping("/reply/create")
    @ApiOperation(value = "유저 댓글작성", notes = "댓글 작성 기능을 구현.")
    public void userReply(@Valid @RequestParam String email, int pid, String content) throws Exception {
		//댓글 업로드 시작!
		Reply reply = new Reply();
		Optional<User> u = userservice.findone(email);
		User rUser = u.get();
		
		Optional<Post> p = postservice.findone(pid);
		Post rPost = p.get();
		
		// Reply : User 정보 이어주기
		reply.setUser(rUser);
		
		// Reply : Post 정보 이어주기
		reply.setPost(rPost);
		reply.setContent(content);
		
		replydao.save(reply);
		System.out.println(reply.getContent());
		System.out.println(reply.getUser().getUid());
	}
	
	@PostMapping("/reply/delete")
	@ApiOperation(value = "댓글 삭제", notes = "댓글  삭제 기능을 구현.")
	public void deleteReply(@Valid @RequestBody int rid) {
		Optional<Reply> reply = replydao.findReplyByRid(rid);

		Reply r = reply.get();

		replydao.delete(r);
	}
}
