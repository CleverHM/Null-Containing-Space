//package com.ssafy.pjt1;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.mail.MessagingException;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.ssafy.pjt1.service.MatchingService;
//
//@SpringBootTest
//class SnsBackendApplicationTests {
//
//	@Autowired
//	MatchingService ms;
//	
////	user1 = new User();
//	
//	@Test
//	void sendMail() throws MessagingException {
//		//customMailSender.sendMail();
//		
//		List<String> preferTech = new ArrayList<>();
//		preferTech.add("java");
//		preferTech.add("sql");
//		preferTech.add("spring");
//		preferTech.add("vue");
//		preferTech.add("algo");
//		
//		System.out.println("aaaa");
//		
//		
//		List<Integer> useridlist = ms.match(1, preferTech);
//		System.out.println(useridlist.size());
//		for(Integer i : useridlist) System.out.println(useridlist.get(i));
//	}
//
//}
