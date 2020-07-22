package com.ssafy.pjt1;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.pjt1.dto.User;

@SpringBootTest
class SnsBackendApplicationTests {

	@Autowired
	
//	user1 = new User();
	
	@Test
	void sendMail() throws MessagingException {
		//customMailSender.sendMail();
	}

}
