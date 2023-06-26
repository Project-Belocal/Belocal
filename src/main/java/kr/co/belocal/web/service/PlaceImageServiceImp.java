package kr.co.belocal.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.PlaceImage;
import kr.co.belocal.web.repository.PlaceImageRepository;

@Service
public class PlaceImageServiceImp implements PlaceImageService {

    @Autowired
    private PlaceImageRepository repository;

    @Override
    public int append(PlaceImage placeImage) {
        return repository.save(placeImage);
    }

    @Override
    public List<PlaceImage> getListByPlaceId(int placeId) {
        
        return repository.findAll(placeId);
    }
    
}

