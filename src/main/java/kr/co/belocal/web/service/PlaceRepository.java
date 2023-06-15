package kr.co.belocal.web.service;

import org.apache.ibatis.annotations.Mapper;

import kr.co.belocal.web.entity.Place;

@Mapper
public interface PlaceRepository {
    void save(Place place);

    Integer getLatestIdByTravelThemeId(Integer travelThemeId);
}
