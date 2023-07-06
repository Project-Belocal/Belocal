package kr.co.belocal.web.controller;


import kr.co.belocal.web.entity.*;
import kr.co.belocal.web.service.ChatService;
import kr.co.belocal.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    //지정된 사용자에게 메세지를 보내는 인터페이스
    private final SimpMessageSendingOperations template;

    @Autowired
    private ChatService chatService;
    @Autowired
    private MemberService memberService;

    //채팅방 목록 조회
    @GetMapping("chat/list")
    public String chatList(Model model) throws ParseException {

        List<ChatRoomListView> list = chatService.findAll(1);

        log.info("list {}",list);

        model.addAttribute("chatList", list);


        return "chat/chatlist";
    }


    //채팅방 id를 받아서 채팅방으로 이동
    @GetMapping("chat/room")
    public String getChatRoom(
            @RequestParam(name = "id") Integer roomId,
            Model model) throws ParseException {

        ChatRoom chatRoom = chatService.findChatRoomById(roomId);
        List<ChatLogListView> chatLog = chatService.chatLogFindAll(roomId);


        model.addAttribute("chatRoom", chatRoom);
        model.addAttribute("chatLog",chatLog);

        return "chat/chatroom";
    }



}
