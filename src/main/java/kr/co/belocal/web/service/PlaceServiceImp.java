package kr.co.belocal.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.Place;
import kr.co.belocal.web.repository.PlaceRepository;

@Service
public class PlaceServiceImp implements PlaceService {

    @Autowired
    private PlaceRepository repository;

    @Override
    public int save(Place place) {
        return repository.save(place);
    }
    
}

