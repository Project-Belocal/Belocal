package kr.co.belocal.web.service;

import java.util.List;

import kr.co.belocal.web.entity.Place;

public interface PlaceService {
    
    int append(Place place);
    
    List<Place> getListByTravelThemeId(int travelThemeId);
}
