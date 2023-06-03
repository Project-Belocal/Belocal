package kr.co.belocal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("profile")
    public String index(){
        return "mypage/profile";
    }
}
