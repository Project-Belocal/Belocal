package kr.co.belocal.web.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.belocal.web.entity.Member;
import org.springframework.data.relational.core.sql.In;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public interface AuthService {
    int save(Member member);
    void addRole(int id);

    boolean login(Member member);


    //아이디, 비번 찾기
    String getFindId(String phoneNum);

    //비밀번호 찾기 , 임시 비밀번호 생성
    String getFindPw(String userId,String phoneNum) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException;



    //중복검사
    String duplicateId(String userId);
    String duplicateNickName(String nickName);
    String  duplicatePhoneNum(String phoneNum);


}
