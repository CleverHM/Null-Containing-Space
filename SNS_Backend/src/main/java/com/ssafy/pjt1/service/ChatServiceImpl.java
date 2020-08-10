package com.ssafy.pjt1.service;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.ssafy.pjt1.dao.ChatRoomDao;
//import com.ssafy.pjt1.dto.ChatRoom;
//
//@Service
//public class ChatServiceImpl implements ChatService{
//	@Autowired
//	ChatRoomDao chatroomdao;
//
//    public List<ChatRoom> findAllRoom() {
//        채팅방 생성순서 최근 순으로 반환
//        List chatRooms = new ArrayList<>(chatRoomMap.values());
//        Collections.reverse(chatRooms);
//        return chatRooms;
//    }
//
//    public ChatRoom findRoomById(String id) {
//        return chatRoomMap.get(id);
//    }
//
//    public ChatRoom createChatRoom(String name) {
//        ChatRoom chatRoom = ChatRoom.create(name);
//        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
//        return chatRoom;
//    }
//}
