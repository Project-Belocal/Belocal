package kr.co.belocal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my-page")
public class MyPageController {

    @RequestMapping("/profile")
    public String profile() {
        return "/my-page/profile";
    }

    @GetMapping("/theme-register")
    public String themeRegister() {
        return "/my-page/theme-register";
    }
}
