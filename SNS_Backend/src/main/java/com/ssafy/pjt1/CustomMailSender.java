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

	public void SingUpSendMail(String email) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setSubject("NULL 담을 공간 - 회원가입을 위한 인증번호 입니다.");

		helper.setTo(email);

		String msg = makeAuth();
		Context context = new Context();
		context.setVariable("test_key", "인증 번호: " + msg);

		// db넣기
		Auth auth = new Auth(email, msg);
		authservice.insert(auth);

		String html = templateEngine.process("mail-template", context);
		helper.setText(html, true);

		javaMailSender.send(message);

	}

	public void passwordUpdateSendMail(String email) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setSubject("NULL 담을 공간 - 비밀번호 변경을 위한 인증번호 입니다.");

		helper.setTo(email);

		String msg = makeAuth();
		Context context = new Context();
		context.setVariable("test_key", "인증 번호: " + msg);

		// db넣기
		Auth auth = new Auth(email, msg);
		authservice.insert(auth);

		String html = templateEngine.process("mail-template", context);
		helper.setText(html, true);

		javaMailSender.send(message);

	}

	static String makeAuth() {
		String auth = "";

		for (int i = 0; i < 5; i++) {
			auth += (int) (Math.random() * 100);
		}

		return auth;
	}
}