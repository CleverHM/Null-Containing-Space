package com.ssafy.pjt1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.pjt1.dao.PostDao;
import com.ssafy.pjt1.dao.PostTagDao;
import com.ssafy.pjt1.dao.TagDao;
import com.ssafy.pjt1.dto.Files;
import com.ssafy.pjt1.dto.Post;
import com.ssafy.pjt1.dto.PostLike;
import com.ssafy.pjt1.dto.PostTag;
import com.ssafy.pjt1.dto.Tag;
import com.ssafy.pjt1.dto.TagFollow;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.dto.UserFollow;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.model.FeedData;
import com.ssafy.pjt1.model.FeedDetailData;
import com.ssafy.pjt1.model.FeedResponse;
import com.ssafy.pjt1.model.HashSearchResponse;
import com.ssafy.pjt1.model.ReplyData;
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

//@CrossOrigin(origins = { "http://localhost:3000" })
@CrossOrigin(origins = "*")

@RestController
public class PostController {
	@Autowired
	private UserService userservice;
	@Autowired
	private TagDao tagdao;
	@Autowired
	private PostDao postdao;
	@Autowired
	private PostTagDao posttagdao;
	@Autowired
	private LikeService likeservice;
	@Autowired
	private PostService postservice;
	// 게시물 작성할때 같이 파일이 넘어옴.
	// 1. 이미지도 저장하고
	// 2. 게시물도 저장하고

// create
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
		// String fileUrl =
		// "/home/ubuntu/s03p12d105/SNS_Backend/src/main/resources/static/images";

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
		post.setFiles(img);

		for (int i = 0; i < hashtags.length; i++) {
			Optional<Tag> optionalTag = tagdao.findTagByName(hashtags[i]);

			// 태그가 테이블에 존재하지 않는 경우.
			if (!optionalTag.isPresent()) {
				Tag t = new Tag(hashtags[i]);
				tagdao.save(t);
				// Post : Tag 정보 이어주기.
				PostTag temp = new PostTag();
				temp.setPost(post);
				temp.setTag(t);
				posttagdao.save(temp);
			}

			// 태그가 테이블에 존재하는 경우.
			else {
				Tag t = optionalTag.get();
				System.out.println("태그 있음");
				// Post : Tag 정보 이어주기.
				PostTag temp = new PostTag();
				temp.setPost(post);
				temp.setTag(t);
				posttagdao.save(temp);
			}
		}

		postdao.save(post);
		System.out.println(post.getContent());
		System.out.println(post.getFiles().getFid());
		System.out.println(post.getTitle());
		System.out.println(post.getUser().getUid());

	}

// 삭제
	@PostMapping("/post/postDelete")
	@ApiOperation(value = "게시물 삭제", notes = "게시물  삭제 기능을 구현.")
	public void postDelete(@Valid @RequestBody String pid) {

		int currentPid = Integer.parseInt(pid);
		Optional<Post> post = postdao.findPostByPid(currentPid);

		Post p = post.get();

		postdao.delete(p);
	}

// update 정보 주기
	@PostMapping("/post/modifyData")
	@ApiOperation(value = "게시물 수정", notes = "게시물  수정 기능을 구현.")
	public FeedDetailData modifyData(@Valid @RequestBody int pid) throws MalformedURLException, IOException {

		// 조회수 추가
		Optional<Post> optionalPost = postdao.findPostByPid(pid);
		List<String> list = new LinkedList<String>();

		FeedDetailData feedDetailData = null;
		Post post = optionalPost.get();

		System.out.println(post.getPid());
		System.out.println(post.getTitle());
		System.out.println(post.getContent());

		for (PostTag t : post.getPosttags()) {
			System.out.println(t.getTag().getName());
			list.add(t.getTag().getName());
		}

//        System.out.println(post.getTags());
		System.out.println(post.getUser().getEmail());

		// 이미지
		byte[] reportBytes = null;
		File result = new File(post.getFiles().getFileurl() + post.getFiles().getFilename());

		if (result.exists()) {
			System.out.println("있음");
			InputStream inputStream = new FileInputStream(post.getFiles().getFileurl() + post.getFiles().getFilename());
			String type = result.toURL().openConnection().guessContentTypeFromName(post.getFiles().getFilename());

			byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("content-disposition", "attachment; filename=" + post.getFiles().getFilename());
			responseHeaders.add("Content-Type", type);

			int count = likeservice.likeCount(post);

			List<ReplyData> reply = new LinkedList<ReplyData>();

			for (int i = 0; i < post.getReplies().size(); i++) {
				reply.add(new ReplyData(post.getReplies().get(i).getRid(), post.getReplies().get(i).getContent(),
						post.getReplies().get(i).getUser().getNickname(), post.getReplies().get(i).getCreateDate(),
						post.getReplies().get(i).getUser().getEmail(), null));
			}

			feedDetailData = new FeedDetailData(post.getPid(), post.getTitle(), post.getContent(), post.getCreateDate(),
					list, post.getUser().getNickname(), post.getUser().getEmail(), out, count, post.getViewCount(), 1,
					reply, reply.size(), null);
		} else {
			System.out.println("없는 파일");
		}

		post.setViewCount(post.getViewCount() + 1);
		postdao.save(post);

		return feedDetailData;
	}

	@PostMapping("/post/modifyTrue")
	@ApiOperation(value = "게시물 수정", notes = "게시물  수정 기능을 구현.")
	public void modifytrue(@Valid @RequestParam int pid, MultipartFile files, String email, String title,
			String content, String[] hashtags) throws MalformedURLException, IOException {

		Optional<Post> p = postdao.findById(pid);
		Post post = p.get();

		// 파일 업로드 시작!
		Files img = new Files();

		String sourceFileName = files.getOriginalFilename();
		System.out.println(sourceFileName);
		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
		File destinationFile;
		String destinationFileName;
		String fileUrl = "C:/s03p12d105/SNS_Backend/src/main/resources/static/images";
		// String fileUrl =
		// "/home/ubuntu/s03p12d105/SNS_Backend/src/main/resources/static/images";

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
		// Post post = new Post();
		Optional<User> u = userservice.findone(email);
		User pUser = u.get();

		post.setUser(pUser);
		post.setTitle(title);
		post.setContent(content);
		// Post : Files 정보이어주기
		post.setFiles(img);

		// 기존꺼지우기
		posttagdao.deletePosttag(pid);

		Set<PostTag> pt = post.getPosttags();
		pt = post.getPosttags();

		for (int i = 0; i < hashtags.length; i++) {
			Optional<Tag> optionalTag = tagdao.findTagByName(hashtags[i]);

			// 태그가 테이블에 존재하지 않는 경우.
			if (!optionalTag.isPresent()) {
				Tag t = new Tag(hashtags[i]);
				tagdao.save(t);
				// Post : Tag 정보 이어주기.
				PostTag temp = new PostTag();
				temp.setPost(post);
				temp.setTag(t);
				// posttagdao.save(temp);
				pt.add(temp);
			}

			// 태그가 테이블에 존재하는 경우.
			else {
				Tag t = optionalTag.get();
				System.out.println("태그 있음");
				// Post : Tag 정보 이어주기.
				PostTag temp = new PostTag();
				temp.setPost(post);
				temp.setTag(t);
				// posttagdao.save(temp);
				pt.add(temp);
			}
		}

		post.setPosttags(pt);
		postdao.save(post);
	}

	@PostMapping("/post/modifyFalse")
	@ApiOperation(value = "게시물 수정", notes = "게시물  수정 기능을 구현.")
	public void modifyfalse(@Valid @RequestParam int pid, String email, String title, String content, String[] hashtags)
			throws MalformedURLException, IOException {

		Optional<Post> p = postdao.findById(pid);
		Post post = p.get();

		// 게시물 업로드 시작!
		// Post post = new Post();
		Optional<User> u = userservice.findone(email);
		User pUser = u.get();

		post.setUser(pUser);
		post.setTitle(title);
		post.setContent(content);

		// 기존꺼지우기
		posttagdao.deletePosttag(pid);

		Set<PostTag> pt = post.getPosttags();
		pt = post.getPosttags();

		for (int i = 0; i < hashtags.length; i++) {
			Optional<Tag> optionalTag = tagdao.findTagByName(hashtags[i]);

			// 태그가 테이블에 존재하지 않는 경우.
			if (!optionalTag.isPresent()) {
				Tag t = new Tag(hashtags[i]);
				tagdao.save(t);
				// Post : Tag 정보 이어주기.
				PostTag temp = new PostTag();
				temp.setPost(post);
				temp.setTag(t);
				// posttagdao.save(temp);
				pt.add(temp);
			}

			// 태그가 테이블에 존재하는 경우.
			else {
				Tag t = optionalTag.get();
				System.out.println("태그 있음");
				// Post : Tag 정보 이어주기.
				PostTag temp = new PostTag();
				temp.setPost(post);
				temp.setTag(t);
				// posttagdao.save(temp);
				pt.add(temp);
			}
		}

		post.setPosttags(pt);
		postdao.save(post);
	}

	// 내게시물 보내주기
	@PostMapping("/post/myPost")
	@ApiOperation(value = "내게시물 Vue로보내기", notes = "내게시물 Vue로보내기 기능을 구현.")
	public List<FeedData> myPost(@Valid @RequestParam String nickname) throws FileNotFoundException, IOException {

		List<FeedData> res = new LinkedList<FeedData>();
		List<Post> postList = new LinkedList<>();

		Optional<User> optionalUser = userservice.findtwo(nickname);
		User user = optionalUser.get();

		// 자기가 올린 게시문
		Set<Post> myPost = user.getPosts();
		postList.addAll(myPost);

		// 게시물 확인
		System.out.println("==============내게시물==================");
		for (int i = 0; i < postList.size(); i++) {
			System.out.println(postList.get(i).getTitle());
		}

		// 데이터 담는 작업
		for (int i = 0; i < postList.size(); i++) {
			System.out.println(postList.get(i).getTitle());
			List<String> tag = new LinkedList<String>();

			for (PostTag t : postList.get(i).getPosttags()) {
				tag.add(t.getTag().getName());
			}

			Date d = new Date();

			// 이미지 만들기
			byte[] reportBytes = null;
			File result = new File(postList.get(i).getFiles().getFileurl() + postList.get(i).getFiles().getFilename());

			if (result.exists()) {
				System.out.println("있음");
				InputStream inputStream = new FileInputStream(
						postList.get(i).getFiles().getFileurl() + postList.get(i).getFiles().getFilename());
				byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

				int count = likeservice.likeCount(postList.get(i));

				// 내가 좋아요 했는가?
				int likeFlag = 0;

				Optional<Post> tempP = postservice.findone(postList.get(i).getPid());
				Post post = tempP.get();
				Set<PostLike> postlikes = post.getPostlikes();

				for (PostLike pl : postlikes) {
					// 이미 좋아요한 사람일 경우.
					if (pl.getUser().getUid() == user.getUid()) {
						likeFlag = 1;
						break;
					}
				}

				byte[] reportBytes1 = null;
				File result1 = new File(postList.get(i).getUser().getProfile().getFileurl()
						+ postList.get(i).getUser().getProfile().getFilename());

				if (result1.exists()) {
					System.out.println("있음");
					InputStream inputStream1 = new FileInputStream(postList.get(i).getUser().getProfile().getFileurl()
							+ postList.get(i).getUser().getProfile().getFilename());

					byte[] out1 = org.apache.commons.io.IOUtils.toByteArray(inputStream1);
					res.add(new FeedData(postList.get(i).getPid(), postList.get(i).getUser().getEmail(),
							postList.get(i).getCreateDate(), postList.get(i).getTitle(),
							postList.get(i).getUser().getNickname(), out, tag, count, likeFlag,
							postList.get(i).getReplies().size(), out1, postList.get(i).getViewCount(), false));
				} else {
					res.add(new FeedData(postList.get(i).getPid(), postList.get(i).getUser().getEmail(),
							postList.get(i).getCreateDate(), postList.get(i).getTitle(),
							postList.get(i).getUser().getNickname(), out, tag, count, likeFlag,
							postList.get(i).getReplies().size(), reportBytes1, postList.get(i).getViewCount(), false));
				}
				// respEntity = new ResponseEntity(out, responseHeaders, HttpStatus.OK));
			} else {
				System.out.println("없는 파일");

				int count = likeservice.likeCount(postList.get(i));

				// 내가 좋아요 했는가?
				int likeFlag = 0;

				Optional<Post> tempP = postservice.findone(postList.get(i).getPid());
				Post post = tempP.get();
				Set<PostLike> postlikes = post.getPostlikes();

				for (PostLike pl : postlikes) {
					// 이미 좋아요한 사람일 경우.
					if (pl.getUser().getUid() == user.getUid()) {
						likeFlag = 1;
						break;
					}
				}

				byte[] reportBytes1 = null;
				File result1 = new File(postList.get(i).getUser().getProfile().getFileurl()
						+ postList.get(i).getUser().getProfile().getFilename());

				if (result1.exists()) {
					System.out.println("있음");
					InputStream inputStream1 = new FileInputStream(postList.get(i).getUser().getProfile().getFileurl()
							+ postList.get(i).getUser().getProfile().getFilename());

					byte[] out1 = org.apache.commons.io.IOUtils.toByteArray(inputStream1);
					res.add(new FeedData(postList.get(i).getPid(), postList.get(i).getUser().getEmail(),
							postList.get(i).getCreateDate(), postList.get(i).getTitle(),
							postList.get(i).getUser().getNickname(), null, tag, count, likeFlag,
							postList.get(i).getReplies().size(), out1, postList.get(i).getViewCount(), false));
				} else {
					res.add(new FeedData(postList.get(i).getPid(), postList.get(i).getUser().getEmail(),
							postList.get(i).getCreateDate(), postList.get(i).getTitle(),
							postList.get(i).getUser().getNickname(), null, tag, count, likeFlag,
							postList.get(i).getReplies().size(), reportBytes1, postList.get(i).getViewCount(), false));
				}
			}

		}
		Collections.sort(res, new Comparator<FeedData>() {

			@Override
			public int compare(FeedData o1, FeedData o2) {
				// TODO Auto-generated method stub
				return o2.getPid() - o1.getPid();
			}
		});
		return res;
	}

	// 내가 좋아요한 게시물 보내주기
	@PostMapping("/post/myLikePost")
	@ApiOperation(value = "내가 좋아요한 게시물 Vue로보내기", notes = "내가 좋아요한 게시물 Vue로보내기 기능을 구현.")
	public List<FeedData> myLikePost(@Valid @RequestParam String nickname) throws FileNotFoundException, IOException {

		List<FeedData> res = new LinkedList<FeedData>();
		List<Post> postList = new LinkedList<>();

		Optional<User> optionalUser = userservice.findtwo(nickname);
		User user = optionalUser.get();

		// 전체 게시문
		List<Post> allpost = postdao.findAll();
		List<Post> myLikePostList = new LinkedList<>();

		// 내가 좋아요하 게시물 로 거르기
		for (int i = 0; i < allpost.size(); i++) {

			Optional<Post> tempP = postservice.findone(allpost.get(i).getPid());
			Post post = tempP.get();
			Set<PostLike> postlikes = post.getPostlikes();

			for (PostLike pl : postlikes) {
				// 이미 좋아요한 사람일 경우.
				if (pl.getUser().getUid() == user.getUid()) {
					myLikePostList.add(post);
					break;
				}
			}
		}

		postList.addAll(myLikePostList);

		// 게시물 확인
		System.out.println("==============내게시물==================");
		for (int i = 0; i < postList.size(); i++) {
			System.out.println(postList.get(i).getTitle());
		}

		for (int i = 0; i < postList.size(); i++) {
			System.out.println(postList.get(i).getTitle());
			List<String> tag = new LinkedList<String>();

			for (PostTag t : postList.get(i).getPosttags()) {
				tag.add(t.getTag().getName());
			}
			Date d = new Date();

			// 이미지 만들기
			byte[] reportBytes = null;
			File result = new File(postList.get(i).getFiles().getFileurl() + postList.get(i).getFiles().getFilename());

			if (result.exists()) {
				System.out.println("있음");
				InputStream inputStream = new FileInputStream(
						postList.get(i).getFiles().getFileurl() + postList.get(i).getFiles().getFilename());

				byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

				int count = likeservice.likeCount(postList.get(i));

				// 내가 좋아요 했는가?
				int likeFlag = 0;

				Optional<Post> tempP = postservice.findone(postList.get(i).getPid());
				Post post = tempP.get();
				Set<PostLike> postlikes = post.getPostlikes();

				for (PostLike pl : postlikes) {
					// 이미 좋아요한 사람일 경우.
					if (pl.getUser().getUid() == user.getUid()) {
						likeFlag = 1;
						break;
					}
				}

				byte[] reportBytes1 = null;
				File result1 = new File(postList.get(i).getUser().getProfile().getFileurl()
						+ postList.get(i).getUser().getProfile().getFilename());

				if (result1.exists()) {
					System.out.println("있음");
					InputStream inputStream1 = new FileInputStream(postList.get(i).getUser().getProfile().getFileurl()
							+ postList.get(i).getUser().getProfile().getFilename());

					byte[] out1 = org.apache.commons.io.IOUtils.toByteArray(inputStream1);
					res.add(new FeedData(postList.get(i).getPid(), postList.get(i).getUser().getEmail(),
							postList.get(i).getCreateDate(), postList.get(i).getTitle(),
							postList.get(i).getUser().getNickname(), out, tag, count, likeFlag,
							postList.get(i).getReplies().size(), out1, postList.get(i).getViewCount(), false));
				} else {
					res.add(new FeedData(postList.get(i).getPid(), postList.get(i).getUser().getEmail(),
							postList.get(i).getCreateDate(), postList.get(i).getTitle(),
							postList.get(i).getUser().getNickname(), out, tag, count, likeFlag,
							postList.get(i).getReplies().size(), reportBytes1, postList.get(i).getViewCount(), false));
				}
				// respEntity = new ResponseEntity(out, responseHeaders, HttpStatus.OK));
			} else {
				System.out.println("없는 파일");

				int count = likeservice.likeCount(postList.get(i));

				// 내가 좋아요 했는가?
				int likeFlag = 0;

				Optional<Post> tempP = postservice.findone(postList.get(i).getPid());
				Post post = tempP.get();
				Set<PostLike> postlikes = post.getPostlikes();

				for (PostLike pl : postlikes) {
					// 이미 좋아요한 사람일 경우.
					if (pl.getUser().getUid() == user.getUid()) {
						likeFlag = 1;
						break;
					}
				}

				byte[] reportBytes1 = null;
				File result1 = new File(postList.get(i).getUser().getProfile().getFileurl()
						+ postList.get(i).getUser().getProfile().getFilename());

				if (result1.exists()) {
					System.out.println("있음");
					InputStream inputStream1 = new FileInputStream(postList.get(i).getUser().getProfile().getFileurl()
							+ postList.get(i).getUser().getProfile().getFilename());

					byte[] out1 = org.apache.commons.io.IOUtils.toByteArray(inputStream1);
					res.add(new FeedData(postList.get(i).getPid(), postList.get(i).getUser().getEmail(),
							postList.get(i).getCreateDate(), postList.get(i).getTitle(),
							postList.get(i).getUser().getNickname(), null, tag, count, likeFlag,
							postList.get(i).getReplies().size(), out1, postList.get(i).getViewCount(), false));
				} else {
					res.add(new FeedData(postList.get(i).getPid(), postList.get(i).getUser().getEmail(),
							postList.get(i).getCreateDate(), postList.get(i).getTitle(),
							postList.get(i).getUser().getNickname(), null, tag, count, likeFlag,
							postList.get(i).getReplies().size(), reportBytes1, postList.get(i).getViewCount(), false));
				}
				// respEntity = new ResponseEntity ("File Not Found", HttpStatus.OK);
			}

		}
		Collections.sort(res, new Comparator<FeedData>() {

			@Override
			public int compare(FeedData o1, FeedData o2) {
				// TODO Auto-generated method stub
				return o2.getPid() - o1.getPid();
			}
		});
		return res;
	}

	// 해당이메일 게시물 보내주기
	// 무한 스크롤
	@PostMapping("/post/getPost")
	@ApiOperation(value = "게시물 Vue로보내기", notes = "게시물 Vue로보내기 기능을 구현.")
	public Object getPost(@Valid @RequestParam String email, int pagenum)
			throws FileNotFoundException, IOException {

		List<FeedData> res = new LinkedList<FeedData>();

		System.out.println(email);
		List<Post> postList = new LinkedList<>();
		Optional<User> optionalUser = userservice.findone(email);
		User user = optionalUser.get();

		Set<UserFollow> followList = user.getFollowings();

		// 자기가 올린 게시문
		Set<Post> myPost = user.getPosts();
		postList.addAll(myPost);

		// 게시물 확인
		System.out.println("==============내게시물==================");
		for (int i = 0; i < postList.size(); i++) {
			System.out.println(postList.get(i).getTitle());
		}
		// 팔로우 들의 게시문
		for (UserFollow u : followList) {
			Optional<User> optionalUser1 = userservice.findone(u.getTo().getEmail());
			User user1 = optionalUser1.get();
			Set<Post> followPost = user1.getPosts();
			postList.addAll(followPost);
		}
		
		// 내가 팔로우 0명인가?
		boolean rflag = false;
		if(followList.size() == 0) rflag = true;
		
		// 게시물 확인
		System.out.println("==============내게시물+팔로우==================");
		for (int i = 0; i < postList.size(); i++) {
			System.out.println(postList.get(i).getTitle());
			List<String> tag = new LinkedList<String>();

			for (PostTag t : postList.get(i).getPosttags()) {
				tag.add(t.getTag().getName());
			}
			Date d = new Date();

			// 이미지 만들기
			byte[] reportBytes = null;
			File result = new File(postList.get(i).getFiles().getFileurl() + postList.get(i).getFiles().getFilename());

			if (result.exists()) {
				System.out.println("있음");
				InputStream inputStream = new FileInputStream(
						postList.get(i).getFiles().getFileurl() + postList.get(i).getFiles().getFilename());
				String type = result.toURL().openConnection()
						.guessContentTypeFromName(postList.get(i).getFiles().getFilename());

				System.out.println(type);

				byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.add("content-disposition",
						"attachment; filename=" + postList.get(i).getFiles().getFilename());
				responseHeaders.add("Content-Type", type);

				int count = likeservice.likeCount(postList.get(i));

				// 내가 좋아요 했는가?
				int likeFlag = 0;

				Optional<Post> tempP = postservice.findone(postList.get(i).getPid());
				Post post = tempP.get();
				Set<PostLike> postlikes = post.getPostlikes();

				for (PostLike pl : postlikes) {
					// 이미 좋아요한 사람일 경우.
					if (pl.getUser().getUid() == user.getUid()) {
						likeFlag = 1;
						break;
					}
				}

				byte[] reportBytes1 = null;
				File result1 = new File(postList.get(i).getUser().getProfile().getFileurl()
						+ postList.get(i).getUser().getProfile().getFilename());

				if (result1.exists()) {
					System.out.println("있음");
					InputStream inputStream1 = new FileInputStream(postList.get(i).getUser().getProfile().getFileurl()
							+ postList.get(i).getUser().getProfile().getFilename());

					byte[] out1 = org.apache.commons.io.IOUtils.toByteArray(inputStream1);
					res.add(new FeedData(postList.get(i).getPid(), postList.get(i).getUser().getEmail(),
							postList.get(i).getCreateDate(), postList.get(i).getTitle(),
							postList.get(i).getUser().getNickname(), out, tag, count, likeFlag,
							postList.get(i).getReplies().size(), out1, postList.get(i).getViewCount(), rflag));
				} else {
					res.add(new FeedData(postList.get(i).getPid(), postList.get(i).getUser().getEmail(),
							postList.get(i).getCreateDate(), postList.get(i).getTitle(),
							postList.get(i).getUser().getNickname(), out, tag, count, likeFlag,
							postList.get(i).getReplies().size(), reportBytes1, postList.get(i).getViewCount(), rflag));
				}
				// respEntity = new ResponseEntity(out, responseHeaders, HttpStatus.OK));
			} else {
				System.out.println("없는 파일");

				int count = likeservice.likeCount(postList.get(i));

				// 내가 좋아요 했는가?
				int likeFlag = 0;

				Optional<Post> tempP = postservice.findone(postList.get(i).getPid());
				Post post = tempP.get();
				Set<PostLike> postlikes = post.getPostlikes();

				for (PostLike pl : postlikes) {
					// 이미 좋아요한 사람일 경우.
					if (pl.getUser().getUid() == user.getUid()) {
						likeFlag = 1;
						break;
					}
				}

				byte[] reportBytes1 = null;
				File result1 = new File(postList.get(i).getUser().getProfile().getFileurl()
						+ postList.get(i).getUser().getProfile().getFilename());

				if (result1.exists()) {
					System.out.println("있음");
					InputStream inputStream1 = new FileInputStream(postList.get(i).getUser().getProfile().getFileurl()
							+ postList.get(i).getUser().getProfile().getFilename());

					byte[] out1 = org.apache.commons.io.IOUtils.toByteArray(inputStream1);
					res.add(new FeedData(postList.get(i).getPid(), postList.get(i).getUser().getEmail(),
							postList.get(i).getCreateDate(), postList.get(i).getTitle(),
							postList.get(i).getUser().getNickname(), null, tag, count, likeFlag,
							postList.get(i).getReplies().size(), out1, postList.get(i).getViewCount(), rflag));
				} else {
					res.add(new FeedData(postList.get(i).getPid(), postList.get(i).getUser().getEmail(),
							postList.get(i).getCreateDate(), postList.get(i).getTitle(),
							postList.get(i).getUser().getNickname(), null, tag, count, likeFlag,
							postList.get(i).getReplies().size(), reportBytes1, postList.get(i).getViewCount(), rflag));
				}
				// respEntity = new ResponseEntity ("File Not Found", HttpStatus.OK);
			}

		}

		Collections.sort(res, new Comparator<FeedData>() {

			@Override
			public int compare(FeedData o1, FeedData o2) {
				// TODO Auto-generated method stub
				return o2.getPid() - o1.getPid();
			}
		});

		// page 만큼 자르기
		List<FeedData> pageRes = new LinkedList<FeedData>();
		int cnt = 5;
		int min = pagenum * cnt - cnt;
		int max = pagenum * cnt;

		if (min < res.size()) {
			for (int i = min; i < max; i++) {
				if (i == res.size()) {
					break;
				}
				pageRes.add(res.get(i));
			}
		}


		final FeedResponse result = new FeedResponse();
		result.status = rflag;
		result.data = "success";
		result.feeddata = pageRes;
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

//
	@PostMapping("/post/postDetail")
	@ApiOperation(value = "게시물 디테일 페이지", notes = "게시물 디테일 페이지 기능을 구현.")
	public FeedDetailData postDetail(@Valid @RequestParam String pid, String email) throws IOException {

		Optional<User> optionalUser = userservice.findone(email);
		User user = optionalUser.get();

		FeedDetailData feedDetailData = null;

		// 조회수 추가
		int currentPid = Integer.parseInt(pid);
		Optional<Post> optionalPost = postdao.findPostByPid(currentPid);
		List<String> list = new LinkedList<String>();

		Post post = optionalPost.get();

		System.out.println(post.getPid());
		System.out.println(post.getTitle());
		System.out.println(post.getContent());

		for (PostTag t : post.getPosttags()) {
			System.out.println(t.getTag().getName());
			list.add(t.getTag().getName());
		}

//            System.out.println(post.getTags());
		System.out.println(post.getUser().getEmail());

		// 이미지
		byte[] reportBytes = null;
		File result = new File(post.getFiles().getFileurl() + post.getFiles().getFilename());

		if (result.exists()) {
			System.out.println("있음");
			InputStream inputStream = new FileInputStream(post.getFiles().getFileurl() + post.getFiles().getFilename());
			String type = result.toURL().openConnection().guessContentTypeFromName(post.getFiles().getFilename());

			byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("content-disposition", "attachment; filename=" + post.getFiles().getFilename());
			responseHeaders.add("Content-Type", type);

			int count = likeservice.likeCount(post);

			// 내가 좋아요 했는가?
			int likeFlag = 0;

			Optional<Post> tempP = postservice.findone(post.getPid());
			Post pp = tempP.get();
			Set<PostLike> postlikes = pp.getPostlikes();

			for (PostLike pl : postlikes) {
				// 이미 좋아요한 사람일 경우.
				if (pl.getUser().getUid() == user.getUid()) {
					likeFlag = 1;
					break;
				}
			}

			List<ReplyData> reply = new LinkedList<ReplyData>();

			for (int i = 0; i < post.getReplies().size(); i++) {
				byte[] reportBytes2 = null;
				File result2 = new File(post.getReplies().get(i).getUser().getProfile().getFileurl()
						+ post.getReplies().get(i).getUser().getProfile().getFilename());

				if (result2.exists()) {
					System.out.println("있음");
					InputStream inputStream1 = new FileInputStream(
							post.getReplies().get(i).getUser().getProfile().getFileurl()
									+ post.getReplies().get(i).getUser().getProfile().getFilename());

					byte[] out2 = org.apache.commons.io.IOUtils.toByteArray(inputStream1);

					reply.add(new ReplyData(post.getReplies().get(i).getRid(), post.getReplies().get(i).getContent(),
							post.getReplies().get(i).getUser().getNickname(), post.getReplies().get(i).getCreateDate(),
							post.getReplies().get(i).getUser().getEmail(), out2));
				} else {

					reply.add(new ReplyData(post.getReplies().get(i).getRid(), post.getReplies().get(i).getContent(),
							post.getReplies().get(i).getUser().getNickname(), post.getReplies().get(i).getCreateDate(),
							post.getReplies().get(i).getUser().getEmail(), reportBytes2));
				}

			}

			byte[] reportBytes1 = null;
			File result1 = new File(
					post.getUser().getProfile().getFileurl() + post.getUser().getProfile().getFilename());

			if (result1.exists()) {
				System.out.println("있음");
				InputStream inputStream1 = new FileInputStream(
						post.getUser().getProfile().getFileurl() + post.getUser().getProfile().getFilename());

				byte[] out1 = org.apache.commons.io.IOUtils.toByteArray(inputStream1);
				feedDetailData = new FeedDetailData(post.getPid(), post.getTitle(), post.getContent(),
						post.getCreateDate(), list, post.getUser().getNickname(), post.getUser().getEmail(), out, count,
						post.getViewCount(), likeFlag, reply, reply.size(), out1);
			} else {
				feedDetailData = new FeedDetailData(post.getPid(), post.getTitle(), post.getContent(),
						post.getCreateDate(), list, post.getUser().getNickname(), post.getUser().getEmail(), out, count,
						post.getViewCount(), likeFlag, reply, reply.size(), reportBytes1);
			}

			// respEntity = new ResponseEntity(out, responseHeaders, HttpStatus.OK));
		} else {
			int count = likeservice.likeCount(post);

			// 내가 좋아요 했는가?
			int likeFlag = 0;

			Optional<Post> tempP = postservice.findone(post.getPid());
			Post pp = tempP.get();
			Set<PostLike> postlikes = pp.getPostlikes();

			for (PostLike pl : postlikes) {
				// 이미 좋아요한 사람일 경우.
				if (pl.getUser().getUid() == user.getUid()) {
					likeFlag = 1;
					break;
				}
			}

			List<ReplyData> reply = new LinkedList<ReplyData>();

			byte[] reportBytes1 = null;
			File result1 = new File(
					post.getUser().getProfile().getFileurl() + post.getUser().getProfile().getFilename());

			if (result1.exists()) {
				System.out.println("있음");
				InputStream inputStream1 = new FileInputStream(
						post.getUser().getProfile().getFileurl() + post.getUser().getProfile().getFilename());

				byte[] out1 = org.apache.commons.io.IOUtils.toByteArray(inputStream1);
				feedDetailData = new FeedDetailData(post.getPid(), post.getTitle(), post.getContent(),
						post.getCreateDate(), list, post.getUser().getNickname(), post.getUser().getEmail(), null,
						count, post.getViewCount(), likeFlag, reply, reply.size(), out1);
			} else {
				feedDetailData = new FeedDetailData(post.getPid(), post.getTitle(), post.getContent(),
						post.getCreateDate(), list, post.getUser().getNickname(), post.getUser().getEmail(), null,
						count, post.getViewCount(), likeFlag, reply, reply.size(), reportBytes1);
			}
			System.out.println("없는 파일");
			// respEntity = new ResponseEntity ("File Not Found", HttpStatus.OK);
		}

		post.setViewCount(post.getViewCount() + 1);
		postdao.save(post);

		return feedDetailData;
	}

	// 해당이메일 게시물 보내주기
	@PostMapping("/post/getHashtagPost")
	@ApiOperation(value = "게시물 해쉬태그 클릭시", notes = "게시물 해쉬태그 클릭시 기능을 구현.")
	public Object getHashtagPost(@Valid @RequestParam String email, String[] hashtag, int pagenum)
			throws MalformedURLException, IOException {

		System.out.println(email);

		List<FeedData> res = new LinkedList<FeedData>();

		System.out.println(email);
		List<Post> postList = new LinkedList<>();
		Optional<User> optionalUser = userservice.findone(email);
		User user = optionalUser.get();

		Set<UserFollow> followList = user.getFollowings();

		// 자기가 올린 게시문
		Set<Post> myPost = user.getPosts();
		postList.addAll(myPost);

		// 게시물 확인
		System.out.println("==============내게시물==================");
		for (int i = 0; i < postList.size(); i++) {
			System.out.println(postList.get(i).getTitle());
		}
		// 팔로우 들의 게시문
		for (UserFollow u : followList) {
			Optional<User> optionalUser1 = userservice.findone(u.getTo().getEmail());
			User user1 = optionalUser1.get();
			Set<Post> followPost = user1.getPosts();
			postList.addAll(followPost);
		}

		// 태그들 포함 여부
		List<Post> hasftagPostList = new LinkedList<>();
		// Iterator<Post> iterator = myPost.iterator();

		System.out.println("=======넣는 중==========");
		for (Post p : postList) {
			end: for (PostTag t : p.getPosttags()) {
				for (int i = 0; i < hashtag.length; i++) {
					if (hashtag[i].equals(t.getTag().getName())) {
						hasftagPostList.add(p);
						break end;
					}
				}
			}
		}

		// 내가 팔로우 0명인가?
		boolean rflag = false;
		if(followList.size() == 0) rflag = true;
		
		// 게시물 확인
		System.out.println("==============내게시물+팔로우==================");
		for (int i = 0; i < hasftagPostList.size(); i++) {
			System.out.println(hasftagPostList.get(i).getTitle());
			List<String> tag = new LinkedList<String>();

			for (PostTag t : hasftagPostList.get(i).getPosttags()) {
				tag.add(t.getTag().getName());
			}
			Date d = new Date();

			// 이미지 만들기
			byte[] reportBytes = null;
			File result = new File(
					hasftagPostList.get(i).getFiles().getFileurl() + hasftagPostList.get(i).getFiles().getFilename());

			if (result.exists()) {
				System.out.println("있음");
				InputStream inputStream = new FileInputStream(hasftagPostList.get(i).getFiles().getFileurl()
						+ hasftagPostList.get(i).getFiles().getFilename());
				String type = result.toURL().openConnection()
						.guessContentTypeFromName(postList.get(i).getFiles().getFilename());

				System.out.println(type);

				byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.add("content-disposition",
						"attachment; filename=" + hasftagPostList.get(i).getFiles().getFilename());
				responseHeaders.add("Content-Type", type);

				int count = likeservice.likeCount(hasftagPostList.get(i));

				// 내가 좋아요 했는가?
				int likeFlag = 0;

				Optional<Post> tempP = postservice.findone(hasftagPostList.get(i).getPid());
				Post post = tempP.get();
				Set<PostLike> postlikes = post.getPostlikes();

				for (PostLike pl : postlikes) {
					// 이미 좋아요한 사람일 경우.
					if (pl.getUser().getUid() == user.getUid()) {
						likeFlag = 1;
						break;
					}
				}

				byte[] reportBytes1 = null;
				File result1 = new File(hasftagPostList.get(i).getUser().getProfile().getFileurl()
						+ hasftagPostList.get(i).getUser().getProfile().getFilename());

				if (result1.exists()) {
					System.out.println("있음");
					InputStream inputStream1 = new FileInputStream(
							hasftagPostList.get(i).getUser().getProfile().getFileurl()
									+ hasftagPostList.get(i).getUser().getProfile().getFilename());

					byte[] out1 = org.apache.commons.io.IOUtils.toByteArray(inputStream1);
					res.add(new FeedData(hasftagPostList.get(i).getPid(), hasftagPostList.get(i).getUser().getEmail(),
							hasftagPostList.get(i).getCreateDate(), hasftagPostList.get(i).getTitle(),
							hasftagPostList.get(i).getUser().getNickname(), out, tag, count, likeFlag,
							hasftagPostList.get(i).getReplies().size(), out1, rflag));
				} else {
					res.add(new FeedData(hasftagPostList.get(i).getPid(), hasftagPostList.get(i).getUser().getEmail(),
							hasftagPostList.get(i).getCreateDate(), hasftagPostList.get(i).getTitle(),
							hasftagPostList.get(i).getUser().getNickname(), out, tag, count, likeFlag,
							hasftagPostList.get(i).getReplies().size(), reportBytes1, rflag));
				}
				// respEntity = new ResponseEntity(out, responseHeaders, HttpStatus.OK));
			} else {
				System.out.println("없는 파일");
				int count = likeservice.likeCount(hasftagPostList.get(i));

				// 내가 좋아요 했는가?
				int likeFlag = 0;

				Optional<Post> tempP = postservice.findone(hasftagPostList.get(i).getPid());
				Post post = tempP.get();
				Set<PostLike> postlikes = post.getPostlikes();

				for (PostLike pl : postlikes) {
					// 이미 좋아요한 사람일 경우.
					if (pl.getUser().getUid() == user.getUid()) {
						likeFlag = 1;
						break;
					}
				}

				byte[] reportBytes1 = null;
				File result1 = new File(hasftagPostList.get(i).getUser().getProfile().getFileurl()
						+ hasftagPostList.get(i).getUser().getProfile().getFilename());

				if (result1.exists()) {
					System.out.println("있음");
					InputStream inputStream1 = new FileInputStream(
							hasftagPostList.get(i).getUser().getProfile().getFileurl()
									+ hasftagPostList.get(i).getUser().getProfile().getFilename());

					byte[] out1 = org.apache.commons.io.IOUtils.toByteArray(inputStream1);
					res.add(new FeedData(hasftagPostList.get(i).getPid(), hasftagPostList.get(i).getUser().getEmail(),
							hasftagPostList.get(i).getCreateDate(), hasftagPostList.get(i).getTitle(),
							hasftagPostList.get(i).getUser().getNickname(), null, tag, count, likeFlag,
							hasftagPostList.get(i).getReplies().size(), out1, rflag));
				} else {
					res.add(new FeedData(hasftagPostList.get(i).getPid(), hasftagPostList.get(i).getUser().getEmail(),
							hasftagPostList.get(i).getCreateDate(), hasftagPostList.get(i).getTitle(),
							hasftagPostList.get(i).getUser().getNickname(), null, tag, count, likeFlag,
							hasftagPostList.get(i).getReplies().size(), reportBytes1, rflag));
				}
				// respEntity = new ResponseEntity ("File Not Found", HttpStatus.OK);
			}

		}

		List<FeedData> res1 = new LinkedList<FeedData>();
		res1.add(res.get(0));
		for (int i = 1; i < res.size(); i++) {
			for (int k = 0; k < res1.size(); k++) {
				if (res1.get(k).getPid() != res.get(i).getPid()) {
					res1.add(res.get(i));
				}
			}
		}

		Collections.sort(res1, new Comparator<FeedData>() {

			@Override
			public int compare(FeedData o1, FeedData o2) {
				// TODO Auto-generated method stub
				return o2.getPid() - o1.getPid();
			}
		});

		Set<FeedData> unique = new LinkedHashSet<>(res1);
		List<FeedData> res2 = new LinkedList<>(unique);
		System.out.println(unique.size());
		System.out.println(res2.size());
		
		// page 만큼 자르기
		List<FeedData> pageRes = new LinkedList<FeedData>();
		int cnt = 5;
		int min = pagenum * cnt - cnt;
		int max = pagenum * cnt;

		if (min < res.size()) {
			for (int i = min; i < max; i++) {
				if (i == res2.size()) {
					break;
				}
				pageRes.add(res2.get(i));
			}
		}

		Set<FeedData> unique1 = new LinkedHashSet<>(pageRes);
		

		final FeedResponse result = new FeedResponse();
		result.status = rflag;
		result.data = "success";
		result.hashfeeddata = unique1;
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 해당 해쉬태그가 있는 모든글 보여주기
	@PostMapping("/post/getHashtagPostAll")
	@ApiOperation(value = "게시물 해쉬태그 클릭시", notes = "게시물 해쉬태그 클릭시 기능을 구현.")
	public Object getHashtagPostAll(@Valid @RequestParam String email, String hashtag)
			throws MalformedURLException, IOException {

		Optional<User> optionalUser = userservice.findone(email);
		User user = optionalUser.get();

		List<Post> postList = postservice.findall();
		List<FeedData> res = new LinkedList<FeedData>();

		// 태그들 포함 여부
		List<Post> hasftagPostList = new LinkedList<>();
		// Iterator<Post> iterator = myPost.iterator();

		System.out.println("=======넣는 중==========");
		for (Post p : postList) {
			end: for (PostTag t : p.getPosttags()) {
				if (hashtag.equals(t.getTag().getName())) {
					hasftagPostList.add(p);
					break end;
				}
			}
		}

		// 이 해쉬태그를 팔로우 했는가

		Set<TagFollow> tagfollow = user.getTagfollows();

		boolean flag = false;

		for (TagFollow tf : tagfollow) {
			if (tf.getTag().getName().equals(hashtag)) {
				System.out.println("태그 있음1");
				flag = true;
				break;
			}
		}

		// 게시물 확인
		System.out.println("==============내게시물+팔로우==================");
		for (int i = 0; i < hasftagPostList.size(); i++) {
			System.out.println(hasftagPostList.get(i).getTitle());
			List<String> tag = new LinkedList<String>();

			for (PostTag t : hasftagPostList.get(i).getPosttags()) {
				tag.add(t.getTag().getName());
			}
			Date d = new Date();

			// 이미지 만들기
			byte[] reportBytes = null;
			File result = new File(
					hasftagPostList.get(i).getFiles().getFileurl() + hasftagPostList.get(i).getFiles().getFilename());

			if (result.exists()) {
				System.out.println("있음");
				InputStream inputStream = new FileInputStream(hasftagPostList.get(i).getFiles().getFileurl()
						+ hasftagPostList.get(i).getFiles().getFilename());
				byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

				int count = likeservice.likeCount(hasftagPostList.get(i));

				// 내가 좋아요 했는가?
				int likeFlag = 0;

				Optional<Post> tempP = postservice.findone(hasftagPostList.get(i).getPid());
				Post post = tempP.get();
				Set<PostLike> postlikes = post.getPostlikes();

				for (PostLike pl : postlikes) {
					// 이미 좋아요한 사람일 경우.
					if (pl.getUser().getUid() == user.getUid()) {
						likeFlag = 1;
						break;
					}
				}

				byte[] reportBytes1 = null;
				File result1 = new File(hasftagPostList.get(i).getUser().getProfile().getFileurl()
						+ hasftagPostList.get(i).getUser().getProfile().getFilename());

				if (result1.exists()) {
					System.out.println("있음");
					InputStream inputStream1 = new FileInputStream(
							hasftagPostList.get(i).getUser().getProfile().getFileurl()
									+ hasftagPostList.get(i).getUser().getProfile().getFilename());

					byte[] out1 = org.apache.commons.io.IOUtils.toByteArray(inputStream1);
					res.add(new FeedData(hasftagPostList.get(i).getPid(), hasftagPostList.get(i).getUser().getEmail(),
							hasftagPostList.get(i).getCreateDate(), hasftagPostList.get(i).getTitle(),
							hasftagPostList.get(i).getUser().getNickname(), out, tag, count, likeFlag,
							hasftagPostList.get(i).getReplies().size(), out1, false));
				} else {
					res.add(new FeedData(hasftagPostList.get(i).getPid(), hasftagPostList.get(i).getUser().getEmail(),
							hasftagPostList.get(i).getCreateDate(), hasftagPostList.get(i).getTitle(),
							hasftagPostList.get(i).getUser().getNickname(), out, tag, count, likeFlag,
							hasftagPostList.get(i).getReplies().size(), reportBytes1, false));
				}
				// respEntity = new ResponseEntity(out, responseHeaders, HttpStatus.OK));
			} else {
				System.out.println("없는 파일");
				int count = likeservice.likeCount(hasftagPostList.get(i));

				// 내가 좋아요 했는가?
				int likeFlag = 0;

				Optional<Post> tempP = postservice.findone(hasftagPostList.get(i).getPid());
				Post post = tempP.get();
				Set<PostLike> postlikes = post.getPostlikes();

				for (PostLike pl : postlikes) {
					// 이미 좋아요한 사람일 경우.
					if (pl.getUser().getUid() == user.getUid()) {
						likeFlag = 1;
						break;
					}
				}

				byte[] reportBytes1 = null;
				File result1 = new File(hasftagPostList.get(i).getUser().getProfile().getFileurl()
						+ hasftagPostList.get(i).getUser().getProfile().getFilename());

				if (result1.exists()) {
					System.out.println("있음");
					InputStream inputStream1 = new FileInputStream(
							hasftagPostList.get(i).getUser().getProfile().getFileurl()
									+ hasftagPostList.get(i).getUser().getProfile().getFilename());

					byte[] out1 = org.apache.commons.io.IOUtils.toByteArray(inputStream1);
					res.add(new FeedData(hasftagPostList.get(i).getPid(), hasftagPostList.get(i).getUser().getEmail(),
							hasftagPostList.get(i).getCreateDate(), hasftagPostList.get(i).getTitle(),
							hasftagPostList.get(i).getUser().getNickname(), null, tag, count, likeFlag,
							hasftagPostList.get(i).getReplies().size(), out1, false));
				} else {
					res.add(new FeedData(hasftagPostList.get(i).getPid(), hasftagPostList.get(i).getUser().getEmail(),
							hasftagPostList.get(i).getCreateDate(), hasftagPostList.get(i).getTitle(),
							hasftagPostList.get(i).getUser().getNickname(), null, tag, count, likeFlag,
							hasftagPostList.get(i).getReplies().size(), reportBytes1, false));
				}
				// respEntity = new ResponseEntity ("File Not Found", HttpStatus.OK);
			}

		}

		List<FeedData> res1 = new LinkedList<FeedData>();
		res1.add(res.get(0));
		for (int i = 1; i < res.size(); i++) {
			for (int k = 0; k < res1.size(); k++) {
				if (res1.get(k).getPid() != res.get(i).getPid()) {
					res1.add(res.get(i));
				}
			}
		}

		Collections.sort(res1, new Comparator<FeedData>() {

			@Override
			public int compare(FeedData o1, FeedData o2) {
				// TODO Auto-generated method stub
				return o2.getPid() - o1.getPid();
			}
		});

		Set<FeedData> unique = new LinkedHashSet<>(res1);

		final HashSearchResponse result = new HashSearchResponse();
		result.status = flag;
		result.data = "success";
		result.hashfeeddata = unique;

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
