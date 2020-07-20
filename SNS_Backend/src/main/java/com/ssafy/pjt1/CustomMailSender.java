package com.ssafy.pjt1;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.ssafy.pjt1.dto.Auth;
import com.ssafy.pjt1.service.AuthService;

@Component
public class CustomMailSender {
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	@Autowired
	AuthService authservice;
	
	public void sendMail(String email) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setSubject("test 이메일");
		
		helper.setTo(email);
		
		String msg = "1234";
		Context context = new Context();
		context.setVariable("test_key", "인증 번호: " + msg);
		
		// db넣기
		Auth auth = new Auth(email, msg);
		authservice.insert(auth);
		
		String html = templateEngine.process("mail-template", context);
		helper.setText(html, true);
		
		javaMailSender.send(message);
				
	}
}
