package kr.co.belocal.web.service;

import kr.co.belocal.web.entity.ChatRoom;

import java.util.List;

public interface ChatService {

    //채팅방 전부 불러오기
    List<ChatRoom> findAll(Integer memberId);
}
