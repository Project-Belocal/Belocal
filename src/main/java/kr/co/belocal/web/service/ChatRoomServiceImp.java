package kr.co.belocal.web.service;

import kr.co.belocal.web.entity.ChatLog;
import kr.co.belocal.web.entity.ChatRoomListView;
import kr.co.belocal.web.entity.TravelTheme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.ChatRoom;
import kr.co.belocal.web.repository.ChatRoomRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ChatRoomServiceImp implements ChatRoomService {

    @Autowired
    private ChatRoomRepository repository;

    //채팅방 생성
    @Override
    public int append(ChatRoom chatRoom) {
        return repository.save(chatRoom);
    }


    //채팅방 하나 가져오기
    @Override
    public ChatRoom findChatRoomById(Integer chatRoomId) {
        return repository.findChatRoomById(chatRoomId);
    }

    //채팅방 삭제
    @Override
    public void deletedRoom(Integer chatRoomId) {
        repository.deletedRoom(chatRoomId);
    }

    //채팅목록 조회
    @Override
    public List<ChatRoomListView> findAll(Integer memberId) throws ParseException {
        String outputPattern = "yyyy-MM-dd HH:mm";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        List<ChatRoomListView> list = repository.findAll(memberId);

        for (ChatRoomListView item : list) {
            String inputDate = item.getRegDate();
            Date date = inputFormat.parse(inputDate);
            String formattedDate = outputFormat.format(date);
            item.setRegDate(formattedDate);
        }


        return list;
    }

    @Override
    public void addEnterMessage(ChatLog chatLog) {
        repository.addEnterMessage(chatLog);
    }


    @Override
    public void isAccept(Integer chatRoomId) {
        repository.isAccept(chatRoomId);
    }

    @Override
    public void isReject(Integer chatRoomId) {
        repository.isReject(chatRoomId);
    }


    @Override
    public int getStatus(int memberId, Integer travelThemeId) {
        return repository.findStatus(memberId, travelThemeId);
    }


    @Override
    public ChatRoom getByIds(int memberId, Integer travelThemeId) {
        return repository.findByIds(memberId, travelThemeId);
    }


}
