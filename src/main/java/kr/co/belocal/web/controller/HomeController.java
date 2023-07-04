package kr.co.belocal.web.controller;

import kr.co.belocal.web.entity.Category;
import kr.co.belocal.web.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.belocal.web.service.TravelThemeService;

import java.util.List;


@Controller
public class HomeController {




    @Autowired
    private CategoryService categoryService;

    @GetMapping("index")
    public String index(Model model){
        List<Category> categoryList = categoryService.findAllCtg();


        model.addAttribute("categoryList", categoryList);
        System.out.println("cateList:" + categoryList);

        return "index";
    }


//    @GetMapping("index")
//    public String categoryList() {
//
//        List<Category> categoryList =
//
//        return "index";
//    }


}
