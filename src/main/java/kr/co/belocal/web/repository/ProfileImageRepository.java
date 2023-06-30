package kr.co.belocal.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.belocal.web.entity.ProfileImage;

@Mapper
public interface ProfileImageRepository {
    
    ProfileImage findByMemberId(int memberId);
}
