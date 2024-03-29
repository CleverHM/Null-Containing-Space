package com.ssafy.pjt1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.dao.TagDao;
import com.ssafy.pjt1.dto.Tag;
import com.ssafy.pjt1.dto.TagFollow;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.dto.UserFollow;
import com.ssafy.pjt1.model.BasicResponse;
import com.ssafy.pjt1.model.HashAllData;
import com.ssafy.pjt1.model.PersonData;
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
public class SearchController {

	@Autowired
	UserService userservice;

	@Autowired
	TagDao tagdao;

	// 계정 검색 검색어 포함된 리스트
	@GetMapping("/search/user/{search}/{mynickname}/{pagenum}")
	@ApiOperation(value = "계정 검색", notes = "계정 검색 기능을 구현.")
	public Object postDelete(@PathVariable String search,@PathVariable String mynickname,@PathVariable int pagenum) throws IOException {
		List<PersonData> list = new LinkedList<PersonData>();

		List<User> allUser = userservice.findall();

		for (User u : allUser) {
			if (u.getNickname().contains(search)) {

				byte[] reportBytes1 = null;
				File result1 = new File(u.getProfile().getFileurl() + u.getProfile().getFilename());

				if (result1.exists()) {
					System.out.println("있음");
					InputStream inputStream1 = new FileInputStream(
							u.getProfile().getFileurl() + u.getProfile().getFilename());
					byte[] out1 = org.apache.commons.io.IOUtils.toByteArray(inputStream1);

					if (u.getNickname().equals(mynickname)) {
						PersonData pd = new PersonData(u.getUid(),out1, u.getNickname(), 3);
						list.add(pd);
					} else {
						// 팔로우 여부 확인
						Set<UserFollow> uf = u.getFollowers();

						int f = 0;
						end: for (UserFollow userfollow : uf) {
							if (userfollow.getFrom().getNickname().equals(mynickname)) {
								f = 1;
								break end;
							}
						}
						if (f == 0) {
							PersonData pd = new PersonData(u.getUid(),out1, u.getNickname(), 0);
							list.add(pd);
						} else {
							PersonData pd = new PersonData(u.getUid(),out1, u.getNickname(), 1);
							list.add(pd);
						}
					}
				} else {
					if (u.getNickname().equals(mynickname)) {
						PersonData pd = new PersonData(u.getUid(),reportBytes1, u.getNickname(), 3);
						list.add(pd);
					} else {
						// 팔로우 여부 확인
						Set<UserFollow> uf = u.getFollowers();

						int f = 0;
						end: for (UserFollow userfollow : uf) {
							if (userfollow.getFrom().getNickname().equals(mynickname)) {
								f = 1;
								break end;
							}
						}
						if (f == 0) {
							PersonData pd = new PersonData(u.getUid(),reportBytes1, u.getNickname(), 0);
							list.add(pd);
						} else {
							PersonData pd = new PersonData(u.getUid(), reportBytes1, u.getNickname(), 1);
							list.add(pd);
						}
					}
				}
			}
		}
		
		Collections.sort(list, new Comparator<PersonData>() {
			@Override
			public int compare(PersonData o1, PersonData o2) {
				// TODO Auto-generated method stub
				return o1.getId() - o2.getId();
			}
		});
		
        // 10개씩 보내기
		List<PersonData> listPage = new LinkedList<PersonData>();
		int cnt = 10;
		int min = pagenum * cnt - cnt;
		int max = pagenum * cnt;

		if (min < list.size()) {
			for (int i = min; i < max; i++) {
				if (i == list.size()) {
					break;
				}
				listPage.add(list.get(i));
			}
		}
		
		return listPage;
	}

	// 해쉬태그 검색 검색어 포함된 리스트
	@GetMapping("/search/hashtag/{hashtag}/{pagenum}/{nickname}")
	@ApiOperation(value = "hashtag 검색", notes = "hashtag 기능을 구현.")
	public Object hashtag(@PathVariable String hashtag, @PathVariable int pagenum, @PathVariable String nickname) throws IOException {
		Optional<User> optionalUser = userservice.findtwo(nickname);
		User user = optionalUser.get();
		
		List<HashAllData> list = new LinkedList<HashAllData>();
		
		List<Tag> allTag = tagdao.findAll();

		for (Tag t : allTag) {
			if (t.getName().contains(hashtag)) {
				// 팔로우 여부 확인
				Set<TagFollow> tagfollow = user.getTagfollows();

				boolean flag = false;

				for (TagFollow tf : tagfollow) {
					if (tf.getTag().getName().equals(t.getName())) {
						System.out.println("태그 있음1");
						flag = true;
						break;
					}
				}
				
				list.add(new HashAllData(t.getTid(), t.getName(), flag));
			}
		}
		
		Collections.sort(list, new Comparator<HashAllData>() {

			@Override
			public int compare(HashAllData o1, HashAllData o2) {
				// TODO Auto-generated method stub
				return o1.getId() - o2.getId();
			}
		});
	
        // 10개씩 보내기
		List<HashAllData> listPage = new LinkedList<HashAllData>();
		int cnt = 10;
		int min = pagenum * cnt - cnt;
		int max = pagenum * cnt;

		if (min < list.size()) {
			for (int i = min; i < max; i++) {
				if (i == list.size()) {
					break;
				}
				listPage.add(list.get(i));
			}
		}
		
		return listPage;
	}
}
