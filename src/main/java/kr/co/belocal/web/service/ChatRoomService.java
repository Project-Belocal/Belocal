package kr.co.belocal.web.service;

import kr.co.belocal.web.entity.ChatRoom;
import kr.co.belocal.web.entity.ChatRoomListView;

import java.text.ParseException;
import java.util.List;

public interface ChatRoomService {

    int append(ChatRoom chatRoom);

    //채팅방 하나 가져오기
    ChatRoom findChatRoomById(Integer chatRoomId);

    //채팅방 삭제
    void deletedRoom(Integer chatRoomId);

    //채팅방 리스트 조회
    List<ChatRoomListView> findAll(Integer memberId) throws ParseException;
}
