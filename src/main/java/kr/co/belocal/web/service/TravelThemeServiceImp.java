package kr.co.belocal.web.service;

import java.util.List;

import kr.co.belocal.web.entity.Category;
import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.TravelThemeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.TravelTheme;
import kr.co.belocal.web.repository.TravelThemeRepository;

@Service
public class TravelThemeServiceImp implements TravelThemeService {

    @Autowired
    private TravelThemeRepository repository;





//====== member-profile 페이지 용 =================
    @Override
    public List<TravelThemeView> getListByMemberId(Integer id, int offset) {
//    public List<TravelTheme> getListByMemberId(Integer id) {
        // 0-5, 6-11, 12-17, 18-23, 24-29
        // (현재페이지번호-1) * 페이지당 요청 자료 개수
        // (0-1) * 5 = 0
        // (2-1) * 5 = 5 '+1'
        // (3-1) * 5 = 10 '+1'

        return repository.findAll(id, offset, 6);
//        return repository.findAll(id, 0, 6);
    }



    @Override
    public List<TravelTheme> getList() {
        return null;
    }


//
//    @Override
//    public List<TravelTheme> getList(Integer memberId) {
//        return null;
//    }

    @Override
    public List<TravelTheme> getList(String categoryName, String location, String description) {
        return null;
    }



    @Override
    public Member getById(Integer memberId, Integer travelThemeId) {
        return null;
    }
    @Override
    public int save(TravelTheme travelTheme) {
        return repository.save(travelTheme);
    }
    @Override
    public TravelTheme get(Integer id) {
        return repository.findById(id);
    }


}//class





