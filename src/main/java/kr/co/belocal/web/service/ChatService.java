package kr.co.belocal.web.service;


import kr.co.belocal.web.entity.*;

import java.text.ParseException;
import java.util.List;

public interface ChatService {

    //채팅로그 조회
    List<ChatLogListView> chatLogFindAll(Integer chatRoomId) throws ParseException;

    //채팅기록 저장
    void addLog(ChatLog chatLog);

    //채팅 확인 유무 업뎃
    void chatUpdate(ChatLog chatLog);

}
