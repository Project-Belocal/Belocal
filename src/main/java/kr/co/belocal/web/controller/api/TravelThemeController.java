package kr.co.belocal.web.controller.api;


import kr.co.belocal.web.entity.TravelThemeView;
import kr.co.belocal.web.service.TravelThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("apiTravelThemeController")
@RequestMapping("/api/travel-themes")
public class TravelThemeController {

    @Autowired
    private TravelThemeService travelThemeService;

    @GetMapping
    public ResponseEntity<List<TravelThemeView>> list
            (
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "c", required = false) Integer ctgId,
            @RequestParam(name = "offset") int offset
            )
    {

        int size = 6;

        List<TravelThemeView> result = null;

        if(query != null){
           result=travelThemeService.getListByQuery(query, offset, size);
        } else if (ctgId != null){
            result=travelThemeService.getListByCtgId(ctgId, offset, size);
        } else {
            result=travelThemeService.getList(offset, size);
        }




        return new ResponseEntity<List<TravelThemeView>>(result, HttpStatus.OK);
    }

}//class






