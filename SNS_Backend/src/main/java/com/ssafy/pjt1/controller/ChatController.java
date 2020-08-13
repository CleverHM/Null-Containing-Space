package com.ssafy.pjt1.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt1.dao.ChatDao;
import com.ssafy.pjt1.dto.Chat;
import com.ssafy.pjt1.dto.Team;
import com.ssafy.pjt1.service.TeamService;

@RestController
@CrossOrigin(origins = "*")
public class ChatController {
	@Autowired
	TeamService teamservice;
	
	@Autowired
	ChatDao chatdao;
	
	private final SimpMessagingTemplate template;
	
	@Autowired
	public ChatController(SimpMessagingTemplate template) {
		this.template = template;
	}
	
	
	// 처음에 채팅 화면이 나왔을때, DB에 저장된 대화 내용 뿌려주기.
	@GetMapping("/chat/initialize/{teamId}")
	public List<Chat> init(@PathVariable int teamId) {
		System.out.println(teamId);
		System.out.println("222222222222222222");
		Optional<Team> optionalTeam = teamservice.findone(teamId);
		Team team = optionalTeam.get();
		List<Chat> chats = team.getChat();
		return chats;
	}
	
	@MessageMapping("/chat/join")
	//@SendTo("/send/teamId")
	public void join(Chat chat) {
		chat.setContent(chat.getContent() + "님이 입장하셨습니다.");
		template.convertAndSend("/subscribe/chat/" + chat.getFakeid(), chat);
	}
	
	
	@Transactional
	@MessageMapping("/chat/message")
	public void message(Chat chat) {
		System.out.println(chat.getUserName());
		System.out.println(chat.getContent());
		
		// 채팅이랑 팀이랑 이어줘야함.
		Optional<Team> optionalTeam = teamservice.findone(chat.getFakeid());
		Team team = optionalTeam.get();
		team.getChat().add(chat);
		chat.setTeam(team);
		chatdao.save(chat);
		teamservice.join(team);
		
		template.convertAndSend("/subscribe/chat/" + chat.getFakeid(), chat);
	}
}
