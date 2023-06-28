package kr.co.belocal.web.service;


import kr.co.belocal.web.entity.ChatRoom;
import kr.co.belocal.web.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService{


    @Autowired
    private ChatRepository chatRepository;

    @Override
    public List<ChatRoom> findAll(Integer memberId) {
        List<ChatRoom> list = chatRepository.findAll(memberId);
        return list;
    }
}
