package kr.co.belocal.web.repository;

import kr.co.belocal.web.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryRepository {
    List<Category> findAllCtg();
    List<Category> getResultCtg();
}
