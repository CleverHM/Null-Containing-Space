package com.ssafy.pjt1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt1.dao.ImageDao;
import com.ssafy.pjt1.dto.Image;

@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	ImageDao imagedao;
	
	public void upload(Image image) {
		Image i = new Image();
		i.setName(image.getName());
		i.setUploadname(image.getUploadname());
		i.setImageurl(image.getImageurl());
		imagedao.save(i);
	}
}
