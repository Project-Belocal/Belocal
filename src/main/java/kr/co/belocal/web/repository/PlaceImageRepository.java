package kr.co.belocal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.belocal.web.entity.PlaceImage;

@Mapper
public interface PlaceImageRepository {
    int save(PlaceImage placeImage);

    List<PlaceImage> findAll(int placeId);

    PlaceImage getPlaceImg(Integer placeId);
}
