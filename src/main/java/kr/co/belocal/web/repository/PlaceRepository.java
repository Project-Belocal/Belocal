package kr.co.belocal.web.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.belocal.web.entity.Place;
import kr.co.belocal.web.entity.PlaceView;

@Mapper
public interface PlaceRepository {
    int save(Place place);

    List<Place> findAll(int travelThemeId);

    List<PlaceView> findViewAll(int travelThemeId);
}
