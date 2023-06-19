package kr.co.belocal.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.PlaceImage;
import kr.co.belocal.web.repository.PlaceImageRepository;

@Service
public class PlaceImageServiceImp implements PlaceImageService {

    @Autowired
    private PlaceImageRepository repository;

    @Override
    public int save(PlaceImage placeImage) {
        return repository.save(placeImage);
    }
    
}

