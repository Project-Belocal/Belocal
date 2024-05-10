package kr.co.belocal.web.repository;


import kr.co.belocal.web.entity.ChatLog;
import kr.co.belocal.web.entity.ChatLogListView;
import kr.co.belocal.web.entity.ChatRoom;
import kr.co.belocal.web.entity.ChatRoomListView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRepository {


    //채팅내역 조회
    List<ChatLogListView> chatLogFindAll(Integer chatRoomId);

    //채팅로그 저장
    void addLog(ChatLog chatLog);

    //채팅 확인 유무 업데이트
    void chatUpdate(ChatLog chatLog);


}
