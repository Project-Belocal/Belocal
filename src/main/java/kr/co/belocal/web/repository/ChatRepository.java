package kr.co.belocal.web.repository;


import kr.co.belocal.web.entity.ChatLog;
import kr.co.belocal.web.entity.ChatLogListView;
import kr.co.belocal.web.entity.ChatRoom;
import kr.co.belocal.web.entity.ChatRoomListView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRepository {

    //채팅방 목록 조회
    List<ChatRoomListView> findAll(Integer memberId);

    //채팅내역 조회
    List<ChatLogListView> chatLogFindAll(Integer chatRoomId);

    //채팅방 하나 가져오기
    ChatRoom findChatRoomById(Integer chatRoomId);

    //채팅방 생성하기

    //채팅로그 저장
    ChatLog addLog(ChatLog chatLog);
}
