package kr.co.belocal.web.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.MemberRoleView;
import kr.co.belocal.web.entity.ProfileImage;
import org.springframework.data.relational.core.sql.In;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;


public interface AuthService {
    int save(Member member);
    //가이드 권한 조회
    Integer FindByRole(Integer memberId);
    //회원가입시 권한
    void addRole(int id);
    //가이드 권한 추가
    void addGuideRole(Integer memberId);


    //Security login check
    Member login(String username);

    //권한조회
    List<MemberRoleView> getMemberRole(Integer memberId);

    //아이디
    String findByUserId(String phoneNum);

    //비밀번호 찾기 , 임시 비밀번호 생성
    String getFindPw(String userId,String phoneNum) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException;

    //현재 비밀번호 확인
    Integer  checkPw(Integer memberId,String pw);

    //중복검사
    String isIdDuplicate(String userId);
    String isNicknameDuplicate(String nickname);
    String isPhoneNumDuplicate(String phoneNum);

    //기본이미지 저장
    void addDefaultImg(Integer memberId);


}
