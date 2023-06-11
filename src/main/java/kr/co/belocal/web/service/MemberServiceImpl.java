package kr.co.belocal.web.service;

import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;


    //아이디 찾기
    @Override
    public String getListByFindId(String phoneNum) {
        return memberRepository.findId(phoneNum);
    }

    //비밀번호 찾기
    @Override
    public String getListByFindId(String userId, String phoneNum) {
        return memberRepository.findPw(userId,phoneNum);
    }


    //회원가입
    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }


    //로그인
    @Override
    public Member login(Member member) {
        Member info = memberRepository.login(member);
        if (info!=null){
            return info;
        }
        return null;
    }

    @Override
    public String checkId(String userId, String type) {
        return memberRepository.checkId(userId);
    }


}
