package kr.co.belocal.web.repository;

import kr.co.belocal.web.entity.TravelTheme;
import kr.co.belocal.web.entity.TravelThemeView;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TravelThemeRepository {

//====== member-profile 페이지용 ===========
    List<TravelThemeView> findAllByMemberId(Integer id, int offset, int size);




//******** api/ travelThemeController *******************
    List<TravelThemeView> findAll(Integer ctgId, String query, int offset, int size);


//====== main화면 기본 6개 나오는 부분 =================
    List<TravelThemeView> getListForMain(int offset, int size);


//====== search-modal : 검색용 =================
    List<TravelThemeView> getListByModalInput(String query, int offset, int size);



//====== search-result : 값 입력 & enter =================
    List<TravelThemeView> getListByQuery(String query, int offset, int size);
//    List<TravelThemeView> getListByCtgId(Integer ctgId, int offset, int size);
    List<TravelThemeView> getListByCtgId(Integer ctgId);




    // theme List 모두 불러오기
    List<TravelTheme> getList();

    TravelTheme findById(Integer id);

    // theme 을 member id 에 맞게 불러오기
    List<TravelTheme> findByMemberId(Integer memberId);

    // theme 저장(save) 하기
    int save(TravelTheme travelTheme);

    // theme 수정(update) 하기
    int update(TravelTheme travelTheme);


    // theme 삭제(delete) 하기
    int delete(TravelTheme travelTheme);

    List<TravelThemeView> findAllViewByIds(List<Integer> travelThemeIdList);

}
