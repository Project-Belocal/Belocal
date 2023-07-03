package kr.co.belocal.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.repository.WishlistRepository;

@Service
public class WishlistServiceImp implements WishlistService {

    @Autowired
    private WishlistRepository repository;

    @Override
    public int getCountsByTravelTheme(int travelThemeId) {
        return repository.countsByTravelTheme(travelThemeId);
    }
    
}
