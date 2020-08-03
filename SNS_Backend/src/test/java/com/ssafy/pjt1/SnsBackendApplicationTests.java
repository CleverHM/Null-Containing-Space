package com.ssafy.pjt1;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.pjt1.service.MatchingService;

@SpringBootTest
class SnsBackendApplicationTests {

	@Autowired
	MatchingService ms;
	
//	user1 = new User();
	
	@Test
	void sendMail() throws MessagingException {
		//customMailSender.sendMail();
		
		List<String> back = new ArrayList<>();
		List<String> front = new ArrayList<>();
		List<String> database = new ArrayList<>();
		List<String> frame = new ArrayList<>();
		String algo;
		back.add("java");
		front.add("html");
		database.add("sql");
		frame.add("vue");
		frame.add("spring");
		algo="algo";
		
		System.out.println("aaaa");
		
		ms.match(back, front, database, frame, algo);
	}

}
