package kr.co.belocal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.belocal.web.entity.WishlistGroup;
import kr.co.belocal.web.entity.WishlistGroupView;

@Mapper
public interface WishlistGroupRepository {
    int findStatus(int travelThemeId, int memberId);
    int save(WishlistGroup wishlistGroup);
    List<WishlistGroupView> findViewAll(int memberId);
}
