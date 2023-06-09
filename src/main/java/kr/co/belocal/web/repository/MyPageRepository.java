package kr.co.belocal.web.repository;

import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.TravelTheme;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageRepository {

    List<Member> findAll(Integer id);

    List<TravelTheme> themeList(Integer userId);

}
