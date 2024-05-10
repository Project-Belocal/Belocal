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

        //--- main 화면의 카테고리 아이콘들 보여주는 것
        List<Category> categoryList = categoryService.findAllCtg();

        //--- main 화면의 기본 6개 띄워주는 것
        List<TravelThemeView> travelThemeList = travelThemeService.getList(0, 6);

        for(TravelThemeView t : travelThemeList) {
            System.out.println(t);
        }

        model.addAttribute("ctgList", categoryList);
        model.addAttribute("travelThemeList", travelThemeList);

        return "index";
    }

}//class
