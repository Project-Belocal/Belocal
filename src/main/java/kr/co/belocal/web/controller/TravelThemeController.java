//package kr.co.belocal.web.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import kr.co.belocal.web.entity.TravelTheme;
//import kr.co.belocal.web.service.TravelThemeService;
//
//@Controller
//public class TravelThemeController {
//
//    @Autowired
//    private TravelThemeService service;
//
//    @GetMapping("/theme-list")
//    public String getList(Model model) {
//
////        List<TravelTheme> list = service.getList();
//        model.addAttribute("model", model);
//        return "theme/theme_list";
//    }
//}
