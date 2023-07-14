package kr.co.belocal.web.service;

import kr.co.belocal.web.entity.Category;
import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.TravelTheme;
import kr.co.belocal.web.entity.TravelThemeView;

import java.util.List;

public interface TravelThemeService {

//====== member-profile 페이지 용 =================
    List<TravelThemeView> getListByMemberId(Integer id, int offset);

//====== main화면 기본 6개 나오는 부분 =================
//    List<TravelThemeView> getListForMain(int offset);

//====== search-modal : 검색용 =================
    List<TravelThemeView> getListByQuery(String query);
    List<TravelThemeView> getListByCtgId(Integer ctgId);



        List<TravelTheme> getList();

    // 'search-result' 페이지에서 검색어에 맞는 검색결과에 해당하는 테마리스트 보여주기
//    List<TravelTheme> getList(String categoryName, String location, String description);

    // 'theme-detail' 페이지 상단
//    Category findById(Integer id);

    TravelTheme get(Integer id);

    // 'theme-detail' 페이지 하단 문자로 박아놓는 시간, 날짜임
    Member getById(Integer memberId, Integer travelThemeId);

    int save(TravelTheme travelTheme);

    List<TravelThemeView> getAllViewByIds(List<Integer> travelThemeIdList);

}
