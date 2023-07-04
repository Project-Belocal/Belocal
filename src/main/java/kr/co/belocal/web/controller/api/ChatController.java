package kr.co.belocal.web.controller.api;

import kr.co.belocal.web.entity.*;
import kr.co.belocal.web.service.ChatService;
import kr.co.belocal.web.service.MemberService;
import kr.co.belocal.web.service.security.MemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController("apiChatController")
@RequestMapping("api/chats")
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

        model.addAttribute("chatList", list);
        log.info("SHOW ALL ChatList {}", list);

        return "chat/chatlist";
    }


    //채팅방 id를 받아서 채팅방으로 이동
    @GetMapping("chat/room")
    public String getChatRoom(
            @RequestParam(name = "id") Integer roomId,
            Model model) {

        ChatRoom chatRoom = chatService.findChatRoomById(roomId);
        List<ChatLogListView> chatLog = chatService.chatLogFindAll(roomId);


        model.addAttribute("chatRoom", chatRoom);
        model.addAttribute("chatLog",chatLog);

        return "chat/chatroom";
    }


    //MessageMapping을 통해 webSocket으로 들어오는 메세지를 발신 처리
    //이때 클라이언트에서 /pub/chat/message 로 요청, 그 요청을 컨트롤러에서 처리
    //처리가 완료된다면  /sub/chat/room/roomId로 메세지가 전송
    @MessageMapping("chat/sendMessage")
    public void sendMessage(@Payload ChatLog chat) {
        System.out.println("Received chat message: " + chat.getMessage());
        System.out.println("Chat Room ID: " + chat.getChatRoomId());
        System.out.println("Member ID: " + chat.getMemberId());


        //chatLog의 메세지에 입력받은값 저장
        chat.setMessage(chat.getMessage());
        //지정된 사용자에게 입력받은값을 전송
        template.convertAndSend("/sub/chat/room/" + chat.getChatRoomId(), chat);

        ChatLog test = ChatLog
                .builder()
                .chatRoomId(chat.getChatRoomId())
                .memberId(chat.getMemberId())
                .message(chat.getMessage())
                .build();

        chatService.addLog(test);
    }
}
