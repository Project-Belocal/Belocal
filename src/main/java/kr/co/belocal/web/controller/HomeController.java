package kr.co.belocal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/index")
    public String index(Model model) {
        
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        
        return "login";
    }

    @RequestMapping("/sign-up")
    public String join(Model model) {
        
        return "sign-up";
    }
    
}
