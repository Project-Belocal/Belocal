package kr.co.belocal.web.service;

import kr.co.belocal.web.entity.Category;
import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.TravelTheme;

import java.util.List;

public interface TravelThemeService {

    List<TravelTheme> getList();

    List<TravelTheme> getListByMemberId(Integer id);

    
    // 'user-profile' 유저가 올린 테마들 모두 보여주는 것
    List<TravelTheme> getList(Integer memberId);

    // 'search-result' 페이지에서 검색어에 맞는 검색결과에 해당하는 테마리스트 보여주기
    List<TravelTheme> getList(String categoryName, String location, String description);

    // 'theme-detail' 페이지 상단
    Category findById(Integer memberId, Integer travelThemeId);

    TravelTheme get(Integer id);

    // 'theme-detail' 페이지 하단 문자로 박아놓는 시간, 날짜임
    Member getById(Integer memberId, Integer travelThemeId);

    int save(TravelTheme travelTheme);

}
