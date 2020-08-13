//package com.ssafy.pjt1.service;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.ssafy.pjt1.dao.ChatRoomDao;
//import com.ssafy.pjt1.dto.Chatroom;
//
//@Service
//public class ChatServiceImpl implements ChatService{
//	@Autowired
//	ChatRoomDao chatroomdao;
//
//	@Override
//    public List<Chatroom> findAllRoom() {
//        // 채팅방 생성순서 최근 순으로 반환
//        List<Chatroom> chatRooms = chatroomdao.findAll();
//        Collections.reverse(chatRooms);
//        return chatRooms;
//    }
//
//    @Override
//	public Optional<Chatroom> findRoomById(int roomId) {
//    	return chatroomdao.findById(roomId);
//	}
//    
//    @Override
//    public Chatroom createChatRoom() {
//        Chatroom chatRoom = new Chatroom();
//        chatroomdao.save(chatRoom);
//        return chatRoom;
//    }
//
//
//	@Override
//	public void deleteChatRoom(int roomId) {
//		chatroomdao.deleteById(roomId);
//	}
//}
