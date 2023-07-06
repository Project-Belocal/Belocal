package kr.co.belocal.web.service;

import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.ProfileImage;

public interface MemberService {
    Member getById(Integer id);

    Member getByIdMember(Integer id);

    Integer editSave(Member member);

    ProfileImage getProfileImg(Integer memberId);
}
