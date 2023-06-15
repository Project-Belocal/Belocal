package kr.co.belocal.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.belocal.web.entity.Place;
import kr.co.belocal.web.entity.PlaceImage;
import kr.co.belocal.web.entity.TravelTheme;
import kr.co.belocal.web.service.TravelThemeService;

@Controller
@RequestMapping("/my-page")
public class MyPageController {

    @Autowired
    private TravelThemeService travelThemeService;

    @RequestMapping("/profile")
    public String profile() {
        return "/my-page/profile";
    }

    @GetMapping("/theme-register")
    public String themeRegister() {
        return "/my-page/theme-register";
    }

    @PostMapping("/upload-theme")
    public ResponseEntity<Integer> uploadTravelTheme(@RequestBody TravelTheme travelTheme) {
        travelTheme.setMemberId(2);
        Integer id = travelThemeService.saveAndGetId(travelTheme);

        return ResponseEntity.ok().body(id);
    }

    @PostMapping("/upload-place")
    public ResponseEntity<Integer> uploadPlace(@RequestBody Place place) {
        
        return null;
    }

    @PostMapping("/upload-img")
    public ResponseEntity<PlaceImage> uploadImage() {
        return null;
    }
}
