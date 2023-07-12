package kr.co.belocal.web.repository;

import kr.co.belocal.web.entity.ChatRoom;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatRoomRepository {

    int save(ChatRoom chatRoom);
}
