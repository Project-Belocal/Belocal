package kr.co.belocal.web.service;

import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.Place;

@Service
public class PlaceServiceImp implements PlaceService {

    private PlaceRepository repository;

    @Override
    public Integer saveAndGetId(Place place) {
        repository.save(place);
        Integer placeId = repository.getLatestIdByTravelThemeId(place.getTravelThemeId());

        return placeId;
    }
    
}
