package kr.co.belocal.web.service;


import kr.co.belocal.web.entity.Category;
import kr.co.belocal.web.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> findAllCtg() {
        return categoryRepository.findAllCtg();
    }

    @Override
    public List<Category> getResultCtg() {
        return categoryRepository.getResultCtg();
    }
}
