### Spring Boot 메일 전송 : Spring Boot 2, Mail, Thymeleaf



#### 1. Maven dependency(pom.xml) 설정

```
<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-mail</artifactId>
</dependency>
<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

####  

#### 2. src/main/resources/application.properties 설정

```
## thymeleaf default settings
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.mode=HTML
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.check-template-location=true
## thymeleaf custom settings
spring.thymeleaf.cache=false

## mail default settings
spring.mail.default-encoding=UTF-8
## mail custom settings
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=yourid@gmail.com
spring.mail.password=yourpw
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.debug=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.mime.charset=UTF-8
spring.mail.properties.mail.transport.protocol=smtp
```



#### 3. CustomMailSender Class 생성

```
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

		// 메일 제목 설정
		helper.setSubject("NULL 담을 공간 - 회원가입을 위한 인증번호 입니다.");
		
		// 수신자 설정
		helper.setTo(email);
		
		// 템플릿에 전달할 데이터 설정
		String msg = makeAuth();
		Context context = new Context();
		context.setVariable("test_key", "인증 번호: " + msg);

		// eamil_auth_db넣기
		Auth auth = new Auth(email, msg);
		authservice.insert(auth);

		// 메일 내용 설정 : 템플릿 프로세스
		String html = templateEngine.process("mail-template", context);
		helper.setText(html, true);
	
		// 메일 보내기
		javaMailSender.send(message);

	}

	public void passwordUpdateSendMail(String email) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		// 메일 제목 설정
		helper.setSubject("NULL 담을 공간 - 비밀번호 변경을 위한 인증번호 입니다.");

		// 수신자 설정
		helper.setTo(email);
		
		// 템플릿에 전달할 데이터 설정
		String msg = makeAuth();
		Context context = new Context();
		context.setVariable("test_key", "인증 번호: " + msg);

		// eamil_auth_db넣기
		Auth auth = new Auth(email, msg);
		authservice.insert(auth);

		// 메일 내용 설정 : 템플릿 프로세스
		String html = templateEngine.process("mail-template", context);
		helper.setText(html, true);
	
		// 메일 보내기
		javaMailSender.send(message);

	}
	
	// 인증 번호 생성 부분
	static String makeAuth() {
		String auth = "";

		for (int i = 0; i < 5; i++) {
			auth += (int) (Math.random() * 100);
		}

		return auth;
	}
}
```



#### 4. src/main/resources/templates/mail-template.html 생성

```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <span th:text="${test_key}"></span>
</body>
</html>
```



#### 5. 인증 메일 보내보기(실행 결과)

- ##### 5-1  인증메일을 보낼 주소 입력

![mailAuth1](C:\Users\multicampus\Desktop\mailAuth1.PNG)



- ##### 5-2  위에서 입력한 이메일 메일함으로 들어가서 인증 번호 확인

![emailAuthResult](C:\Users\multicampus\Desktop\emailAuthResult.PNG)



- ##### 5-3 확인한 인증 번호로 이메일 인증 하기

![](C:\Users\multicampus\Desktop\mailAuth2.PNG)

