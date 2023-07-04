package kr.co.belocal.web.service;



import kr.co.belocal.web.entity.*;
import kr.co.belocal.web.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatRepository chatRepository;

    //채팅목록 조회
    @Override
    public List<ChatRoomListView> findAll(Integer memberId) throws ParseException {
        String outputPattern = "yyyy-MM-dd HH:mm";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        List<ChatRoomListView> list = chatRepository.findAll(memberId);

        for (ChatRoomListView item : list) {
            String inputDate = item.getRegDate();
            Date date = inputFormat.parse(inputDate);
            String formattedDate = outputFormat.format(date);
            item.setRegDate(formattedDate);
        }


        return list;
    }

    //채팅방 하나 가져오기
    @Override
    public ChatRoom findChatRoomById(Integer chatRoomId) {
        return chatRepository.findChatRoomById(chatRoomId);
    }

    //채팅방 생성하기
    @Override
    public ChatRoom createRoom(Integer travelThemeId, Integer travelerId, Integer guideId) {
        return null;
    }


    @Override
    public List<ChatLogListView> chatLogFindAll(Integer chatRoomId) {
        return chatRepository.chatLogFindAll(chatRoomId);
    }

    @Override
    public ChatLog addLog(ChatLog chatLog) {
        return chatRepository.addLog(chatLog);
    }
}
