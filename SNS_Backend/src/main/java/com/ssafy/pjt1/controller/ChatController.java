package com.ssafy.pjt1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.pjt1.dto.Chat;
//import com.ssafy.pjt1.service.ChatService;
import com.ssafy.pjt1.service.TeamService;

@Controller
public class ChatController {
	@Autowired
	TeamService teamservice;
	
//	@Autowired
//	ChatService chatservice;
	
	private final SimpMessagingTemplate template;
	
	@Autowired
	public ChatController(SimpMessagingTemplate template) {
		this.template = template;
	}
	
	@MessageMapping("/chat/join")
	//@SendTo("/send/teamId")
	public void join(Chat chat) {
		chat.setContent(chat.getContent() + "님이 입장하셨습니다.");
		template.convertAndSend("/subscribe/chat/" + chat.getFakeid(), chat);
	}
	
	
	@MessageMapping("/chat/message")
	public void message(Chat chat) {
		System.out.println(chat.getUserName());
		System.out.println(chat.getContent());
		template.convertAndSend("/subscribe/chat/" + chat.getFakeid(), chat);
	}
	
	
//	public void SocketHandler(Chat chat) {
//
//		String userName = chat.getUserName();
//		String content = chat.getContent();
//		System.out.println(teamId);
//		int teamId = chat.getTeam().getTeamid();
//		
//		System.out.println(userName);
//		System.out.println(content);
//		
//		
//		Chat result = new Chat();
//		result.setUserName(userName);
//		result.setContent(content);
//		
//		System.out.println("fffgggggf" + chat.getFakeid());
//
//		smt.convertAndSend("localhost:3000/subscribe/chat/" + chat.getFakeid(), result);
//	
//	}
	
	
//	@GetMapping("/room/enter/{teamId}")
//	public void roomEnter(@PathVariable int teamId) {
//		Optional<Team> temp = teamservice.findone(teamId);
//		Team team = temp.get();
//		return team.getChatroom();
//	}
}
