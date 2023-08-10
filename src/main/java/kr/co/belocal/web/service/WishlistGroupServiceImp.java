package kr.co.belocal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.WishlistGroup;
import kr.co.belocal.web.entity.WishlistGroupView;
import kr.co.belocal.web.repository.WishlistGroupRepository;

@Service
public class WishlistGroupServiceImp implements WishlistGroupService {

    @Autowired
    private WishlistGroupRepository repository;
    
    @Override
    public int getStatus(int travelThemeId, int memberId) {
        return repository.findStatus(travelThemeId, memberId);
    }

    @Override
    public int append(WishlistGroup wishlistGroup) {
        return repository.save(wishlistGroup);
    }

    @Override
    public List<WishlistGroupView> getViewListByMemberId(int memberId) {
        return repository.findViewAll(memberId);
    }

    @Override
    public WishlistGroup getById(int wishlistGroupId) {
        return repository.findById(wishlistGroupId);
    }
    
}
