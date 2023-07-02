package kr.co.belocal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.Place;
import kr.co.belocal.web.entity.PlaceView;
import kr.co.belocal.web.repository.PlaceRepository;

@Service
public class PlaceServiceImp implements PlaceService {

    @Autowired
    private PlaceRepository repository;

    @Override
    public int append(Place place) {
        return repository.save(place);
    }

    @Override
    public List<Place> getListByTravelThemeId(int travelThemeId) {
        
        return repository.findAll(travelThemeId);
    }

    @Override
    public List<PlaceView> getViewListByTravelThemeId(int travelThemeId) {
        return repository.findViewAll(travelThemeId);
    }
    
}

