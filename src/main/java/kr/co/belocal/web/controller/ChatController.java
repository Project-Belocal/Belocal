package kr.co.belocal.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chat")
public class ChatController {


    @GetMapping("list")
    public String chatList(){

        return "chat/chatlist";
    }
}
