package kr.co.belocal.web.controller.api;

import kr.co.belocal.web.entity.ChatRoom;
import kr.co.belocal.web.service.ChatService;
import kr.co.belocal.web.service.security.MemberDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("apiChatController")
@RequestMapping("api/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping
    public List<ChatRoom> list(){

        List<ChatRoom> list = new ArrayList<>();
        list = chatService.findAll(91);


        System.out.println("list = " + list);

        return list;
    }




}
