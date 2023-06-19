package kr.co.belocal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.belocal.web.service.TravelThemeService;


@Controller
public class HomeController {

    @GetMapping("/")
    public String test(){
        return "test";
    }

    @GetMapping("index")
    public String index(){

        return "index";
    }
}
