package kr.co.belocal.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.belocal.web.entity.Place;

@Mapper
public interface PlaceRepository {
    void save(Place place);

    Integer getLatestIdByTravelThemeId(Integer travelThemeId);
}
