package kr.co.belocal.web.controller;

import java.util.List;

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
import kr.co.belocal.web.service.PlaceImageService;
import kr.co.belocal.web.service.PlaceService;
import kr.co.belocal.web.service.TravelThemeService;

@Controller
@RequestMapping("/my-page")
public class MyPageController {

    @Autowired
    private TravelThemeService travelThemeService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PlaceImageService placeImageService;

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
        int result = travelThemeService.save(travelTheme);

        return ResponseEntity.ok().body(travelTheme.getId());
    }

    @PostMapping("/upload-place")
    public ResponseEntity<Integer> uploadPlace(@RequestBody Place place) {
        int result = placeService.save(place);

        return ResponseEntity.ok().body(place.getId());
    }

    @PostMapping("/upload-img")
    public ResponseEntity<Integer> uploadImage(@RequestBody List<List<PlaceImage>> placesImages) {
        for(int i = 0 ; i < placesImages.size(); i++) {
            List<PlaceImage> list = placesImages.get(i);

            for(int j = 0; j < list.size(); j++) {
                placeImageService.save(list.get(j));
            }
        }

        return ResponseEntity.ok().body(1);
    }
}
