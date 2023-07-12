package kr.co.belocal.web.controller.api;


import kr.co.belocal.web.entity.TravelThemeView;
import kr.co.belocal.web.service.TravelThemeService;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("apiSearchController")
@RequestMapping("api/search-result")
public class SearchController {

    @Autowired
    private TravelThemeService service;

    @GetMapping("")
    public ResponseEntity<List<TravelThemeView>> getListByCtgId(
        @RequestParam(name="ctg") Integer ctgId
    ){
        List<TravelThemeView> travelThemeViewList = service.getListByCtgId(ctgId);

        System.out.println("ctgId:"+ctgId);

        return  ResponseEntity.ok().body(travelThemeViewList);
    }

}//class
