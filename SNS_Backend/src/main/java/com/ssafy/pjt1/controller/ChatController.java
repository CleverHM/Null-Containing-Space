package com.ssafy.pjt1.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.ssafy.pjt1.dto.Chat;


@Controller
public class ChatController {
	@MessageMapping("/receive")
	@SendTo("/send")
	
	public Chat SocketHandler(Chat chat) {
		String userName = chat.getUserName();
		String content = chat.getContent();
		Chat result = new Chat(userName, content);
		return result;
	}
}
