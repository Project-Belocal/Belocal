package kr.co.belocal.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.ProfileImage;
import kr.co.belocal.web.repository.ProfileImageRepository;

@Service
public class ProfileImageServiceImp implements ProfileImageService{

    @Autowired
    private ProfileImageRepository repository;

    @Override
    public ProfileImage getByMemberId(int memberId) {

        return repository.findByMemberId(memberId);
    }
    
}
