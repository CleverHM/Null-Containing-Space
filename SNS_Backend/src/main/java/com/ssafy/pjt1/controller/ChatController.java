package com.ssafy.pjt1.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.pjt1.dto.Chat;
import com.ssafy.pjt1.dto.Chatroom;
import com.ssafy.pjt1.dto.Team;
import com.ssafy.pjt1.service.ChatService;
import com.ssafy.pjt1.service.TeamService;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	TeamService teamservice;
	
	@Autowired
	ChatService chatservice;
	
	@MessageMapping("/receive")
	@SendTo("/send")
	
	public Chat SocketHandler(Chat chat) {
		String userName = chat.getUserName();
		String content = chat.getContent();
		
		int teamId = chat.getRoom().getTeam().getTeamid();
		System.out.println(teamId);
		
		Optional<Team> temp = teamservice.findone(teamId);
		Team team = temp.get();
		Chatroom chatroom = team.getChatroom();
		
		Chat result = new Chat(userName, content);
		chatroom.getChat().add(result);
		
		return result;
	}
	
	
//	@GetMapping("/room/enter/{teamId}")
//	public void roomEnter(@PathVariable int teamId) {
//		Optional<Team> temp = teamservice.findone(teamId);
//		Team team = temp.get();
//		return team.getChatroom();
//	}
}
