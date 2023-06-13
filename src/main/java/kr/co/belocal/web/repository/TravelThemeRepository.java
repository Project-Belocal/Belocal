package kr.co.belocal.web.repository;

import kr.co.belocal.web.entity.TravelTheme;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface TravelThemeRepository {

    // theme List 모두 불러오기
    List<TravelTheme> findAll();

//    List<TravelTheme> findAll(Integer id);


    // theme 을 member id 에 맞게 불러오기
    List<TravelTheme> findByMemberId(Integer memberId);


    // theme 저장(save) 하기
    int save(TravelTheme travelTheme);


    // theme 수정(update) 하기
    int update(TravelTheme travelTheme);


    // theme 삭제(delete) 하기
    int delete(TravelTheme travelTheme);

}
