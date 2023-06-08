package kr.co.belocal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.belocal.web.entity.Theme;

@Mapper
public interface ThemeRepository {
    
    List<Theme> findAll();
}
