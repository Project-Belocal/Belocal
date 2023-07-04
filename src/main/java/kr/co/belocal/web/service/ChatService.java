package kr.co.belocal.web.service;


import kr.co.belocal.web.entity.*;

import java.text.ParseException;
import java.util.List;

public interface ChatService {
    //채팅방 리스트 조회
    List<ChatRoomListView> findAll(Integer memberId) throws ParseException;

    //채팅방 하나 가져오기
    ChatRoom findChatRoomById(Integer chatRoomId);

    //채팅방 생성
    ChatRoom createRoom(Integer travelThemeId,Integer travelerId,Integer guideId);

    //채팅로그 조회
    List<ChatLogListView> chatLogFindAll(Integer chatRoomId);

    //채팅기록 저장
    ChatLog addLog(ChatLog chatLog);
}
