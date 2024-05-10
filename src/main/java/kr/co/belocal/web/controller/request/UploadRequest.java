package kr.co.belocal.web.controller.request;

import java.util.List;

import kr.co.belocal.web.entity.Place;
import kr.co.belocal.web.entity.PlaceImage;
import kr.co.belocal.web.entity.TravelTheme;

public record UploadRequest(
    TravelTheme travelTheme,
    List<Place> places,
    List<List<PlaceImage>> placesImages
) {
    
}
