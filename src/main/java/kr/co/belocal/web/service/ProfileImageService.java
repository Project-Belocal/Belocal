package kr.co.belocal.web.service;

import kr.co.belocal.web.entity.ProfileImage;

public interface ProfileImageService {
    ProfileImage getByMemberId(int memberId);
}
