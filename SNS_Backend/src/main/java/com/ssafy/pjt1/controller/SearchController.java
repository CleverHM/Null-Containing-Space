package com.ssafy.pjt1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.dao.TagDao;
import com.ssafy.pjt1.dto.Tag;
import com.ssafy.pjt1.dto.User;
import com.ssafy.pjt1.model.BasicResponse;
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
	@PostMapping("/search/user")
	@ApiOperation(value = "계정 검색", notes = "계정 검색 기능을 구현.")
	public Object postDelete(@Valid @RequestParam String search, String mynickname) throws IOException {
		List<PersonData> list = new LinkedList<PersonData>();
		
		List<User> allUser = userservice.findall();
		
		for(User u : allUser) {
			if(u.getNickname().equals(mynickname)) {
				continue;
			}
			else if(u.getNickname().contains(search)) {
				
				byte[] reportBytes1 = null;
				File result1 = new File(u.getProfile().getFileurl()
						+ u.getProfile().getFilename());

				if (result1.exists()) {
					System.out.println("있음");
					InputStream inputStream1 = new FileInputStream(u.getProfile().getFileurl()
							+ u.getProfile().getFilename());
					byte[] out1 = org.apache.commons.io.IOUtils.toByteArray(inputStream1);
					
					// 팔로우 여부 확				
					PersonData pd = new PersonData(out1, u.getNickname(), 0);
					list.add(pd);
				} else {
					PersonData pd = new PersonData(reportBytes1, u.getNickname(), 0);
					list.add(pd);
				}
			}
		}
		
		return list;
	}
	
	// 해쉬태그 검색 검색어 포함된 리스트
	@PostMapping("/search/hashtag")
	@ApiOperation(value = "hashtag 검색", notes = "hashtag 기능을 구현.")
	public Object hashtag(@Valid @RequestParam String hashtag) throws IOException {
		List<String> list = new LinkedList<String>();
		
		List<Tag> allTag = tagdao.findAll();
		
		for(Tag t : allTag) {
			if(t.getName().contains(hashtag)) {
				list.add(t.getName());
			}
		}
		
		return list;
	}
}
