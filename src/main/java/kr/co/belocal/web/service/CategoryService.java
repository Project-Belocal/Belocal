package kr.co.belocal.web.service;

import kr.co.belocal.web.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCtg();

}
