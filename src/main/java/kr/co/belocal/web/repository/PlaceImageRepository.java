package kr.co.belocal.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.belocal.web.entity.PlaceImage;

@Mapper
public interface PlaceImageRepository {
    int save(PlaceImage placeImage);

}
