package kr.co.belocal.web.service;

import java.util.List;

import kr.co.belocal.web.entity.Category;
import kr.co.belocal.web.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.Category;
import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.TravelTheme;
import kr.co.belocal.web.repository.TravelThemeRepository;
import org.springframework.ui.context.Theme;

@Service
public class TravelThemeServiceImp implements TravelThemeService {

    @Autowired
    private TravelThemeRepository travelThemeRepository;





//====== member-profile 페이지 용 =================
    @Override
    public List<TravelTheme> getListByMemberId(Integer id) {

        return travelThemeRepository.findAll(id);
    }



    @Override
    public List<TravelTheme> getList() {
        return null;
    }

    @Override
    public List<TravelTheme> getList(Integer memberId) {
        return null;
    }

    @Override
    public List<TravelTheme> getList(String categoryName, String location, String description) {
        return null;
    }

    @Override
    public Category findById(Integer memberId, Integer travelThemeId) {
        return null;
    }

    @Override
    public Member getById(Integer memberId, Integer travelThemeId) {
        return null;
    }
    @Override
    public int save(TravelTheme travelTheme) {
        return travelThemeRepository.save(travelTheme);
    }


}//class





