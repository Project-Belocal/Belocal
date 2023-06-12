package kr.co.belocal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.belocal.web.entity.TravelTheme;

@Mapper
public interface WishlistRepository {
    
    List<TravelTheme> findAll(int memberId);
}