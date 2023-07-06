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


@Controller("/index")
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TravelThemeService travelThemeService;

    @GetMapping("index")
    public String index(
            @RequestParam(name="offset", defaultValue = "0")int offset,
            Model model){
        List<Category> categoryList = categoryService.findAllCtg();
        List<TravelThemeView> travelThemeList = travelThemeService.getListForMain(offset);

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("travelThemeList", travelThemeList);

        return "index";
    }


    @GetMapping("search-result")
    public String search(
            @RequestParam(name="s", required=false)String query,
            @RequestParam(name="ctg", required=false)Integer ctgId,
            Model model){

        List<TravelThemeView> searchThemeList = null;

        if(query != null) {
            searchThemeList = travelThemeService.getListByQuery(query);
        } else if(ctgId != null) {
            searchThemeList = travelThemeService.getListByCtgId(ctgId);
        }
        model.addAttribute("search", searchThemeList);
        model.addAttribute("ctg", searchThemeList);



        return "/search-result";
    }//search








}//class
