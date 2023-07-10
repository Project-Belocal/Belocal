package kr.co.belocal.web.service;

import java.util.List;

import kr.co.belocal.web.entity.WishlistGroup;
import kr.co.belocal.web.entity.WishlistGroupView;

public interface WishlistGroupService {
    
    int append(WishlistGroup wishlistGroup);
    int getStatus(int travelThemeId, int memberId);
    List<WishlistGroupView> getViewListByMemberId(int memberId);
}
