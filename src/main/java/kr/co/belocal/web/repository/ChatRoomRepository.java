package kr.co.belocal.web.repository;

import kr.co.belocal.web.entity.ChatRoom;
import kr.co.belocal.web.entity.ChatRoomListView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatRoomRepository {

    int save(ChatRoom chatRoom);

    //채팅방 하나 가져오기
    ChatRoom findChatRoomById(Integer chatRoomId);

    //채팅방 삭제
    void deletedRoom(Integer chatRoomId);

    //채팅방 목록 조회
    List<ChatRoomListView> findAll(Integer memberId);
}
