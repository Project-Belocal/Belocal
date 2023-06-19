package kr.co.belocal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class MyPageController {

    @GetMapping
    public String my(){
        return "member/my/profile";
    }


    @GetMapping("/profile-edit")
    public String profileEdit(){
        return "member/my/profile-edit";
    }

}
