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
//@RestController("apiSearchController")
//@RequestMapping("api/search-result")
//public class SearchResultController {
//
//    @Autowired
//    private TravelThemeService service;
//
//    @GetMapping("/travel-themes") //--- search-result 페이지의 카테고리 아이콘 클릭시 작동하는 부분
//    public ResponseEntity<List<TravelThemeView>> getListByCtgIdIcons(
//            @RequestParam(name="q") String query,
//            @RequestParam(name = "ctg") Integer ctgId,
//            @RequestParam(name="offset", defaultValue = "0") int offset
//    ) {
//
//
//
//        List<TravelThemeView> travelThemeViewList = null;
//
//        if(ctgId != null) {
//            travelThemeViewList = service.getListByCtgIdIcons(ctgId, offset, 6);
//        } else if(query != null) {
//            travelThemeViewList = service.getListByQuery(query, offset, 6);
//        }
//
//        return ResponseEntity.ok().body(travelThemeViewList);
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
//}//class
