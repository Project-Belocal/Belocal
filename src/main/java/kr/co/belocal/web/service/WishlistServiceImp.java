package kr.co.belocal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.Wishlist;
import kr.co.belocal.web.repository.WishlistRepository;

@Service
public class WishlistServiceImp implements WishlistService {

    @Autowired
    private WishlistRepository repository;

    @Override
    public int getCountsByTravelTheme(int travelThemeId) {
        return repository.countsByTravelTheme(travelThemeId);
    }

    @Override
    public int delete(int travelThemeId, int memberId) {
        return repository.delete(travelThemeId, memberId);
    }

    @Override
    public int append(Wishlist wishlist) {
        return repository.save(wishlist);
    }

    @Override
    public List<Integer> getAllTravelThemeIdByGroupId(int wishlistGroupId) {
        return repository.findAllTravelThemeIdByGroupId(wishlistGroupId);
    }
}
