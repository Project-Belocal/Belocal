package kr.co.belocal.web.repository;

import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.MemberRoleView;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

@Mapper
public interface AuthRepository {

    //아이디 중복 확인
    String duplicateId(String userId);
    //닉네임 중복 확인
    String duplicateNickName(String nickName);
    //휴대폰 중복 확인
    String  duplicatePhoneNum(String phoneNum);

    //회원 권한 조회
    List<MemberRoleView> getMemberRole(Integer memberId);


    //로그인
    Member login(String username);
    //회원 정보 조회
    List<Member> findAll(Integer id);
    //id찾기
    String getFindId(String phoneNum);
    //pw찾기 -> 값이 존재하면 id값 반환
    Integer getFindPw(String userId,String phoneNum);

    //임시 비밀번호로 변경
    void updateTemporaryPwd(Member member);


    //회원가입
    int save(Member member);
    //권한 부여
    void addRole(Integer id);

    //회원 정보 수정
    int update(Member member);
    //계정삭제
    int delete(Member id);


}
