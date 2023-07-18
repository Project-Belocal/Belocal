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
public class SearchResultController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TravelThemeService travelThemeService;

    @GetMapping("search-result")
    public String search(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "ctg", required = false) Integer ctgId,
            @RequestParam(name = "offset", defaultValue = "0", required = false) int offset,
            Model model) {

        List<TravelThemeView> searchThemeList = null;

        int size = 6;

        if (query != null) { //쿼리가 있으면 (검색창에 검색어 입력 & enter)
            searchThemeList = travelThemeService.getListByQuery(query, offset, 6);
        } else if (ctgId != null) { //ctgId가 있으면 (카테고리 아이콘 클릭 시 작동)
            searchThemeList = travelThemeService.getListByCtgId(ctgId, offset, 6);
        } else {
            searchThemeList = travelThemeService.getList(offset, size);
        }

        //---- 검색창에 검색어 입력 & enter 시 해당 결과(theme) 가져오는 기능
        model.addAttribute("search", searchThemeList);

        //---- 검색한 기록 남게하는 기능
        if (query != null) {
            model.addAttribute("query", query);
        }

        //--- search-result 페이지 : (기본적으로 뜨는) 카테고리 아이콘 목록들
        List<Category> getCategoryList = categoryService.getResultCtg();
        model.addAttribute("getCtgId", getCategoryList);


        return "/search-result";
    }//search
}//class

//    @GetMapping("search-result")
//    public String search(
//            @RequestParam(name="s", required=false)String query,
//            @RequestParam(name="ctg", required=false)Integer ctgId,
//            Model model){
//
//        List<TravelThemeView> searchThemeList = null;
//        List<TravelThemeView> ctgThemeList = null;
//
//        if(query != null) { //쿼리가 있으면 (검색창에 검색어 입력 & enter)
//            searchThemeList = travelThemeService.getListByQuery(query);
//        } else if(ctgId != null) { //ctgId가 있으면 (카테고리 아이콘 클릭 시 작동)
//            searchThemeList = travelThemeService.getListByCtgId(ctgId);
//        }
//
//        //---- 검색창에 검색어 입력 & enter 시 해당 결과(theme) 가져오는 기능
//        model.addAttribute("search", searchThemeList);
//
//        //---- 검색한 기록 남게하는 기능
//        if(query != null) {
//            model.addAttribute("query", query);
//        }
//
//        //--- search-result 페이지 : (기본적으로 뜨는) 카테고리 아이콘 목록들
//        List<Category> getCategoryList = categoryService.getResultCtg();
//        model.addAttribute("getCtgId", getCategoryList);
//
//
//
//        return "/search-result";
//    }//search


