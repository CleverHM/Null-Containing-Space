package com.ssafy.pjt1.controller;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.ssafy.pjt1.model.FeedData;
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

	// 해당이메일 게시물 보내주기
	@PostMapping("/post/getPost")
	@ApiOperation(value = "게시물 Vue로보내기", notes = "게시물 Vue로보내기 기능을 구현.")
	public List<FeedData> getPost(@Valid @RequestBody String email) {

		System.out.println(email);
		List<Post> postList = new LinkedList<>();
		List<FeedData> res = new LinkedList<>();
		Optional<User> optionalUser = userservice.findone(email);
		User user = optionalUser.get();
		Set<User> followList = user.getFollowing();
		// 자기가 올린 게시문
		Set<Post> myPost = user.getPosts();
		postList.addAll(myPost);
		// 게시물 확인
		System.out.println("==============내게시물==================");
		for (int i = 0; i < postList.size(); i++) {
			System.out.println(postList.get(i).getTitle());
		}
		// 팔로우 들의 게시문
		for (User u : followList) {
			Optional<User> optionalUser1 = userservice.findone(u.getEmail());
			User user1 = optionalUser1.get();
			Set<Post> followPost = user1.getPosts();
			postList.addAll(followPost);
		}
		// 게시물 확인
		System.out.println("==============내게시물+팔로우==================");
		for (int i = 0; i < postList.size(); i++) {
			System.out.println(postList.get(i).getTitle());
			List<String> tag = new LinkedList<String>();

			for (Tag t : postList.get(i).getTags()) {
				tag.add(t.getName());
			}
			res.add(new FeedData(postList.get(i).getTitle(), postList.get(i).getUser().getNickname(),
					postList.get(i).getImg().getFilename(), postList.get(i).getImg().getFileurl(), tag));
		}
		return res;
	}

	// 해당이메일 게시물 보내주기
	@PostMapping("/post/getHashtagPost")
	@ApiOperation(value = "게시물 해쉬태그 클릭시", notes = "게시물 해쉬태그 클릭시 기능을 구현.")
	public void getHashtagPost(@Valid @RequestParam String email, @Valid @RequestParam String[] hashtag) {
		List<Post> postList = new LinkedList<>();
		Optional<User> optionalUser = userservice.findone(email);
		User user = optionalUser.get();
		Set<User> followList = user.getFollowing();
		// 자기가 올린 게시문
		Set<Post> myPost = user.getPosts();
		postList.addAll(myPost);
		// 팔로우 들의 게시문
		for (User u : followList) {
			Optional<User> optionalUser1 = userservice.findone(u.getEmail());
			User user1 = optionalUser1.get();
			Set<Post> followPost = user1.getPosts();
			postList.addAll(followPost);
		}
		// 게시물 확인
		System.out.println("==============내게시물+팔로우==================");
		for (int i = 0; i < postList.size(); i++) {
			System.out.println(postList.get(i).getTitle());
		}
		// 태그들 포함 여부
		List<Post> hasftagPostList = new LinkedList<>();
		// Iterator<Post> iterator = myPost.iterator();
		for (Post p : postList) {
			System.out.println("=================");
			for (int i = 0; i < hashtag.length; i++) {
				for (Tag t : p.getTags()) {
					if (hashtag[i].equals(t.getName())) {
						hasftagPostList.add(p);
					}
				}
			}
		}
		// 게시물 확인
		System.out.println("==============내게시물+팔로우==================");
		for (int i = 0; i < hasftagPostList.size(); i++) {
			System.out.println(hasftagPostList.get(i).getPid());
		}
		// return hasftagPostList;
	}

}