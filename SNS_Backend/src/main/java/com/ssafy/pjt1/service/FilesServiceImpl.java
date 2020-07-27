package com.ssafy.pjt1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt1.dao.FilesDao;
import com.ssafy.pjt1.dto.Files;

@Service
public class FilesServiceImpl implements FilesService {
	@Autowired
	FilesDao imagedao;
	
	public void upload(Files image) {
		Files i = new Files();
		i.setFilename(image.getFilename());
		i.setFileOriname(image.getFileOriname());
		i.setFileurl(image.getFileurl());
		imagedao.save(i);
	}
}
