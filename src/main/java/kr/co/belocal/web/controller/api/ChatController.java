package kr.co.belocal.web.controller.api;


import kr.co.belocal.web.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("apiChatController")
@RequestMapping("api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

}
