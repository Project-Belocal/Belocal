package kr.co.belocal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.belocal.web.entity.TravelTheme;
import kr.co.belocal.web.service.TravelThemeService;

@Controller
@RequestMapping("/theme")
public class TravelThemeController {

    @Autowired
    private TravelThemeService service;


    @GetMapping("/theme-list")
    public String list(Model model) {

        List<TravelTheme> list = service.getList();
        model.addAttribute("list", list);
        System.out.println(list);
        return "theme/theme_list";
    }

    @GetMapping("theme-detail")
    public String detail(
        @RequestParam(name = "id", required = true) Integer travelThemeId,
        Model model
    ) {
        TravelTheme travelTheme = service.get(travelThemeId);
        // Member member = 
        model.addAttribute("travelTheme", travelTheme);

        return "theme/theme-detail";
    }
}

