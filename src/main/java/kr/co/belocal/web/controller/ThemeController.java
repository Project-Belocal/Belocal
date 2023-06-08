package kr.co.belocal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.belocal.web.entity.Theme;
import kr.co.belocal.web.service.ThemeService;

@Controller
public class ThemeController {
    
    @Autowired
    private ThemeService service;

    @GetMapping("/theme-list")
    public String getList(Model model) {

        List<Theme> list = service.getList();
        model.addAttribute("model", model);
        System.out.println(list);
        return "theme/theme_list";
    }
}
