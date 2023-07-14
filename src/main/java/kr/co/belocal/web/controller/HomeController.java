package kr.co.belocal.web.controller;

import kr.co.belocal.web.entity.Category;
import kr.co.belocal.web.entity.TravelTheme;
import kr.co.belocal.web.entity.TravelThemeView;
import kr.co.belocal.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.belocal.web.service.TravelThemeService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller("/")
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TravelThemeService travelThemeService;

    @GetMapping("/")
    public String index(Model model){
        List<Category> categoryList = categoryService.findAllCtg();
        List<TravelThemeView> travelThemeList = travelThemeService.getListForMain(0);

        model.addAttribute("ctgList", categoryList);
        model.addAttribute("travelThemeList", travelThemeList);

        return "index";
    }


}//class
