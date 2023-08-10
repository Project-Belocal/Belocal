package kr.co.belocal.web.service;

import java.util.List;

import kr.co.belocal.web.entity.PlaceImage;

public interface PlaceImageService {
    
    int append(PlaceImage placeImage);

    List<PlaceImage> getListByPlaceId(int placeId);

    PlaceImage getFirstImageByPlaceId(Integer placeId);
}
