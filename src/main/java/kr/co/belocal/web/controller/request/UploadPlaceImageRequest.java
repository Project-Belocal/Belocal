package kr.co.belocal.web.controller.request;

import java.util.List;

import kr.co.belocal.web.entity.PlaceImage;

public record UploadPlaceImageRequest(
    Integer travelThemeId,
    List<List<PlaceImage>> placesImages
) {
    
}
