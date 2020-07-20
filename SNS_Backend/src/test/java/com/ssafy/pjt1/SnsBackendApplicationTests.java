package com.ssafy.pjt1;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SnsBackendApplicationTests {

	@Autowired
	CustomMailSender customMailSender;
	
	@Test
	void sendMail() throws MessagingException {
		//customMailSender.sendMail();
	}

}
