package kr.co.belocal.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.ChatRoom;
import kr.co.belocal.web.repository.ChatRoomRepository;

@Service
public class ChatRoomServiceImp implements ChatRoomService {

    @Autowired
    private ChatRoomRepository repository;

    @Override
    public int append(ChatRoom chatRoom) {
        return repository.save(chatRoom);
    }
    
}
