package kr.co.belocal.web.repository;

import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.ProfileImage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {

    Member findById(Integer id);
//    Member findByIdMember(Integer id);

    Integer editSave(Member member);

    void updateProfileImg(ProfileImage profileImage);

    ProfileImage getProfileImg(Integer memberId);

    int withdrawal(Member member);
}
