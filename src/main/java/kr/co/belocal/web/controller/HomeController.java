package kr.co.belocal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("test")
    public String test(){
        return "chat/chatlist";
    }
}
