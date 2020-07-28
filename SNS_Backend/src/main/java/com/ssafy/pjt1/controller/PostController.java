package com.ssafy.pjt1.controller;

import java.io.File;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.pjt1.dao.PostDao;
import com.ssafy.pjt1.dao.TagDao;
import com.ssafy.pjt1.dto.Files;
import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.Tag;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.service.FilesService;
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
public class PostController {
	@Autowired
	private UserService userservice;
	@Autowired
	private TagDao tagdao;
	@Autowired
	private PostDao postdao;
	
	// 게시물 작성할때 같이 파일이 넘어옴.
	// 1. 이미지도 저장하고
	// 2. 게시물도 저장하고

	@PostMapping("/post/create")
	@ApiOperation(value = "유저 게시물작성", notes = "게시물 작성 기능을 구현.")
	public void userPost(@Valid @RequestParam MultipartFile files, String email, String title, String content,
			String[] hashtags) throws Exception {

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
		} while (destinationFile.exists());

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

		for (int i = 0; i < hashtags.length; i++) {
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
