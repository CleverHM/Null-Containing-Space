//package com.ssafy.pjt1.controller;
//
//import com.ssafy.pjt1.dto.ChatMessage;
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpAnnotationMethodMessageHandler;
//import org.springframework.stereotype.Controller;
//
//@RequiredArgsConstructor
//@Controller
//public class ChatController {
//	
//	private final SimpAnnotationMethodMessageHandler messagingTemplate;
//	
//	@MessageMapping("/chat/message")
//	public void message(ChatMessage message) {
//        if (ChatMessage.MessageType.ENTER.equals(message.getType()))
//            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
//        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
//    }
//}
