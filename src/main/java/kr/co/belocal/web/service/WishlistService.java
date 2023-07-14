package kr.co.belocal.web.service;

import java.util.List;

import kr.co.belocal.web.entity.Wishlist;

public interface WishlistService {
    int delete(int travelThemeId, int memberId);

    int getCountsByTravelTheme(int travelThemeId);

    int append(Wishlist wishlist);
    
    List<Integer> getAllTravelThemeIdByGroupId(int wishlistGroupId);
}
