package kr.co.belocal.web.controller;


import kr.co.belocal.web.entity.Category;
import kr.co.belocal.web.entity.TravelThemeView;
import kr.co.belocal.web.service.CategoryService;
import kr.co.belocal.web.service.TravelThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller("search-result")
public class SearchController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TravelThemeService travelThemeService;

    @GetMapping("search-result")
    public String search(
            @RequestParam(name="s", required=false)String query,
            @RequestParam(name="ctg", required=false)Integer ctgId,
            Model model){

        List<TravelThemeView> searchThemeList = null;
        List<TravelThemeView> ctgThemeList = null;

        if(query != null) {
            searchThemeList = travelThemeService.getListByQuery(query);
        } else if(ctgId != null) {
            searchThemeList = travelThemeService.getListByCtgId(ctgId);
        }

        List<Category> getCategoryList = categoryService.getResultCtg();

        model.addAttribute("search", searchThemeList);



        //파라미터 없이, 리스트 보여주는 용
        model.addAttribute("getCtgId", getCategoryList);



        return "/search-result";
    }//search

}
