package kr.co.belocal.web.repository;

import kr.co.belocal.web.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface MemberRepository {

    //아이디 중복 확인
    String checkId(String userId);
    //닉네임 중복 확인
    String checkNickName(String nickName);

    //로그인
    Member login(Member member);
    //회원 정보 조회
    List<Member> findAll(Integer id);
    //id찾기
    String findId(String phoneNum);
    //pw찾기
    String findPw(String userId,String phoneNum);
    //회원가입
    void save(Member member);
    //회원 정보 수정
    int update(Member member);
    //계정삭제
    int delete(Member id);




}//class
