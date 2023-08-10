//package kr.co.belocal.web.controller.api;
//
//
//import kr.co.belocal.web.entity.TravelThemeView;
//import kr.co.belocal.web.service.TravelThemeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController("apiSearchModalController")
//@RequestMapping("/api")
//public class SearchModalController {
//
//    @Autowired
//    private TravelThemeService service;
//
//    @GetMapping("")
//    public ResponseEntity<List<TravelThemeView>> getListByModalInput(
//            @RequestParam(name = "q")
//            String query,
//            @RequestParam(name="offset", defaultValue = "0")
//            int offset
//    ) {
//
//        //-- 모달창에서 실시간 검색결과 가져오는 부분
//        List<TravelThemeView> travelThemeViewListModal = null;
//        travelThemeViewListModal = service.getListByQuery(query, offset, 6);
//
//        System.out.println("query:" + query);
//
////        return ResponseEntity.ok().body(travelThemeViewList);
//        return ResponseEntity.ok().body(travelThemeViewListModal);
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}//class
