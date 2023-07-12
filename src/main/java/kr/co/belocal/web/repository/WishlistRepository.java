package kr.co.belocal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.belocal.web.entity.TravelTheme;
import kr.co.belocal.web.entity.Wishlist;

@Mapper
public interface WishlistRepository {
    
    List<TravelTheme> findAll(int memberId);

    int countsByTravelTheme(int travelThemeId);

    int delete(int travelThemeId, int memberId);

    int save(Wishlist wishlist);
}
