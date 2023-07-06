package kr.co.belocal.web.controller.api;

import kr.co.belocal.web.entity.*;
import kr.co.belocal.web.service.ChatService;
import kr.co.belocal.web.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RestController("apiChatController")
@RequestMapping("/chat/api/chats")
public class ChatController {


    //지정된 사용자에게 메세지를 보내는 인터페이스
    private final SimpMessageSendingOperations template;

    @Autowired
    private ChatService chatService;
    @Autowired
    private MemberService memberService;


    @PostMapping("/check")
    public Integer messageChecked(
                @RequestBody Map<String, Object> requestBody
            ){

        Integer chatRoomId = Integer.valueOf((String) requestBody.get("roomId"));
        Integer memberId = Integer.valueOf((String) requestBody.get("id"));

        //memberId -> 내 id
        //내 id가 아닌 log들의 checked를 1로 바꿔야함.
        //내id와 여행객id가 같다면? -> 가이드id를 업데이트
        //내id와 가이드id가 같다면? -> 여행객id를 업데이트
        ChatRoom chatRoom = chatService.findChatRoomById(chatRoomId);
        Integer traveler = chatRoom.getTravelerId();
        Integer guide = chatRoom.getGuideId();
        Integer id = null;

        if (memberId.equals(traveler)) {
            id = guide;
        }else {
            id = traveler;
        }


        ChatLog chatLog = ChatLog
                .builder()
                .memberId(id)
                .chatRoomId(chatRoomId)
                .isChecked(1)
                .build();


//
        chatService.chatUpdate(chatLog);


        return 200;
    }


    //MessageMapping을 통해 webSocket으로 들어오는 메세지를 발신 처리
    //이때 클라이언트에서 /pub/chat/message 로 요청, 그 요청을 컨트롤러에서 처리
    //처리가 완료된다면  /sub/chat/room/roomId로 메세지가 전송
    @MessageMapping("chat/sendMessage")
    public void sendMessage(@Payload ChatLog chat) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedTime = currentTime.format(formatter);

        ProfileImage img = memberService.getProfileImg(chat.getMemberId());
        String uuid = img.getUuid();

        Map<String, Object> combinedData = new HashMap<>();
        combinedData.put("chat", chat);
        combinedData.put("uuid", uuid);

        //chatLog의 메세지에 입력받은값 저장,보낸시간 저장
        chat.setMessage(chat.getMessage());
        chat.setRegDate(formattedTime);
        //지정된 사용자에게 입력받은값을 전송
//        template.convertAndSend("/sub/chat/room/" + chat.getChatRoomId(), chat);
        template.convertAndSend("/sub/chat/room/" + chat.getChatRoomId(), combinedData);


        
        ChatLog add = ChatLog
                .builder()
                .chatRoomId(chat.getChatRoomId())
                .memberId(chat.getMemberId())
                .message(chat.getMessage())
                .regDate(chat.getRegDate())
                .build();

        chatService.addLog(add);
    }







}
