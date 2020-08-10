//package com.ssafy.pjt1;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import javax.mail.MessagingException;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.ssafy.pjt1.dto.Team;
//import com.ssafy.pjt1.dto.User;
//import com.ssafy.pjt1.service.MatchingService;
//import com.ssafy.pjt1.service.MatchingServiceImpl.U;
//import com.ssafy.pjt1.service.UserService;
//
//@SpringBootTest
//class SnsBackendApplicationTests {
//	@Autowired
//	UserService userservice;
//	
//	@Autowired
//	MatchingService ms;
//	
////	user1 = new User();
//	
//	@Test
//	void sendMail() throws MessagingException {
//		//customMailSender.sendMail();
//		List<String> preferTech = new ArrayList<>();
//		
//		String nickname = "1";
//		int preferProject = 1;
//		
//		Optional<User> u = userservice.findtwo(nickname);
//		
//		Team team = u.get().getTeam();
//		
//		if(team.isBack_cpp()==true) preferTech.add("cpp");
//		if(team.isBack_java()==true) preferTech.add("java");
//		if(team.isBack_python()==true) preferTech.add("pyhton");
//		if(team.isBack_php()==true) preferTech.add("php");
//		if(team.isFront_html()==true) preferTech.add("html");
//		if(team.isFront_css()==true) preferTech.add("css");
//		if(team.isFront_javascript()==true) preferTech.add("javascript");
//		if(team.isDb_sql()==true) preferTech.add("sql");
//		if(team.isDb_nosql()==true) preferTech.add("nosql");
//		if(team.isFrame_spring()==true) preferTech.add("spring");
//		if(team.isFrame_django()==true) preferTech.add("django");
//		if(team.isFrame_bootstrap()==true) preferTech.add("bootstrap");
//		if(team.isFrame_vue()==true) preferTech.add("vue");
//		if(team.isFrame_react()==true) preferTech.add("react");
//		if(team.getAlgo()==true) preferTech.add("algo");
//		
//		System.out.println("aaaa");
//		
//		
//		List<U> useridlist = ms.match(preferProject, preferTech);
//		System.out.println(useridlist.size());
//		for(U i : useridlist) {
//			System.out.println("유저 id : " + i.getUserid() + "적합도 : " + i.getPercent());
//		}
//	}
//
//}
