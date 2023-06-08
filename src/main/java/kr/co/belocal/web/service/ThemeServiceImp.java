package kr.co.belocal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.Theme;
import kr.co.belocal.web.repository.ThemeRepository;

@Service
public class ThemeServiceImp implements ThemeService {

    @Autowired
    private ThemeRepository repository;
    @Override
    public List<Theme> getList() {
        List<Theme> list = repository.findAll();

        return list;
    }
    
}
