package kr.co.belocal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.Category;
import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.TravelTheme;
import kr.co.belocal.web.repository.TravelThemeRepository;

@Service
public class TravelThemeServiceImp implements TravelThemeService {

    @Autowired
    private TravelThemeRepository repository;

    @Override
    public List<TravelTheme> getList() {
        List<TravelTheme> list = repository.findAll();

        return list;
    }

    @Override
    public List<TravelTheme> getList(Integer memberId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getList'");
    }

    @Override
    public List<TravelTheme> getList(String categoryName, String location, String description) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getList'");
    }

    @Override
    public Category findById(Integer memeberId, Integer traverThemeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Member getById(Integer memberId, Integer travelThemeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }
    
}
