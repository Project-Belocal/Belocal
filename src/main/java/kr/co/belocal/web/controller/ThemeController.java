package kr.co.belocal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThemeController {
    
    @GetMapping("/theme-list")
    public String getList(Model model) {

        return "theme/theme_list";
    }
}
