package com.ssafy.pjt1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt1.dao.ProfileDao;
import com.ssafy.pjt1.dto.Profile;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	ProfileDao imagedao;
	
	@Override
	public void upload(Profile image) {
		Profile i = new Profile();
		i.setFilename(image.getFilename());
		i.setFileOriname(image.getFileOriname());
		i.setFileurl(image.getFileurl());
		imagedao.save(i);
	}

}
