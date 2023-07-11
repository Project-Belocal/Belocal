package kr.co.belocal.web.controller;


import kr.co.belocal.web.entity.*;
import kr.co.belocal.web.service.*;
import kr.co.belocal.web.service.security.MemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Autowired
    private PlaceService placeService;
    @Autowired
    private PlaceImageService placeImageService;

    @Autowired
    private TravelThemeService travelThemeService;

    //채팅방 목록 조회
    @GetMapping("chat/list")
    public String chatList(@AuthenticationPrincipal MemberDetails member,
                           Model model) throws ParseException {


        List<ChatRoomListView> list = chatService.findAll(member.getId());

//        log.info("list {}",list);


        model.addAttribute("chatList", list);


        return "chat/chatlist";
    }


    //채팅방 id를 받아서 채팅방으로 이동
    @GetMapping("chat/room")
    public String getChatRoom(
            @RequestParam(name = "id") Integer roomId,
            @AuthenticationPrincipal MemberDetails member,
            Model model) throws ParseException {



        ChatRoom chatRoom = chatService.findChatRoomById(roomId);

        Member memberInfo = null;


        if (member.getId().equals(chatRoom.getTravelerId())) {
            memberInfo = memberService.getByIdMember(chatRoom.getGuideId());
        }else {
            memberInfo = memberService.getByIdMember(chatRoom.getTravelerId());
        }

        List<ChatLogListView> chatLog = chatService.chatLogFindAll(roomId);
        Integer themeId = chatRoom.getTravelThemeId();
        TravelTheme theme = travelThemeService.get(themeId);
        Place place = placeService.findById(themeId);
        PlaceImage placeImage = placeImageService.getFirstImageByPlaceId(place.getId());



        model.addAttribute("theme",theme);
        model.addAttribute("place",place);
        model.addAttribute("placeImg",placeImage);
        model.addAttribute("info",memberInfo);
        model.addAttribute("chatRoom", chatRoom);
        model.addAttribute("chatLog",chatLog);

        return "chat/chatroom";
    }



}
