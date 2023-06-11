package kr.co.belocal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
}
