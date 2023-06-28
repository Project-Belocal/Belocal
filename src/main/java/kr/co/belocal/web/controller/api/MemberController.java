package kr.co.belocal.web.controller.api;

import kr.co.belocal.web.entity.TravelTheme;
import kr.co.belocal.web.service.TravelThemeService;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("apiMemberProfileController")
@RequestMapping("api/memberprofile")
public class MemberController {


//    @Autowired
//    private MemberService memberService;

    @Autowired
    private TravelThemeService service;



    @GetMapping("")
    public ResponseEntity<List<TravelTheme>> getListByMemberId(
            @RequestParam(name = "i", required = false, defaultValue="2")Integer id,
            @RequestParam(name = "offset") int offset) {


        List<TravelTheme> travelThemeList = service.getListByMemberId(id, offset);
//        System.out.print(travelThemeList.get(0));

        return ResponseEntity.ok().body(travelThemeList);

    }

}
