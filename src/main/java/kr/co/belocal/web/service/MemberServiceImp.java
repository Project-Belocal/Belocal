package kr.co.belocal.web.service;

import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.repository.AuthRepository;
import kr.co.belocal.web.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class MemberServiceImp implements MemberService{

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;


    @Override
    public Member getById(Integer id) {

        return memberRepository.findById(id);
    }


    //회원정보 수정
    @Override
    public Integer editSave(Member member) {
        String defaultNickName = null;
        String defaultSelfIntroduction = null;
        String defaultPw = null;
        String defaultPhone = null;

        String nickname;
        String selfIntroduction;
        String pw;
        String phone;

        List<Member> list = authRepository.findAll(member.getId());
        for (Member defaultValue : list) {
            defaultNickName = defaultValue.getNickName();
            defaultSelfIntroduction = defaultValue.getSelfIntroduction();
            defaultPw = defaultValue.getPw();
            defaultPhone = defaultValue.getPhoneNum();
        }



        if (member.getNickName().equals("")||member.getNickName()==null)
            nickname = defaultNickName;
        else
            nickname = member.getNickName();


        if (member.getSelfIntroduction().equals("")||member.getSelfIntroduction()==null)
            selfIntroduction = defaultSelfIntroduction;
        else
            selfIntroduction = member.getSelfIntroduction();


        if (member.getPw() == null || member.getPw() == "")
            pw = defaultPw;
        else
            pw = passwordEncoder.encode(member.getPw());


        if (member.getPhoneNum().equals("") || member.getPhoneNum()==null)
            phone = defaultPhone;
        else
            phone = member.getPhoneNum();



        Member input = Member
                .builder()
                .id(member.getId())
                .nickName(nickname)
                .selfIntroduction(selfIntroduction)
                .pw(pw)
                .phoneNum(phone)
                .build();


        Integer result = memberRepository.editSave(input);

        return result;
    }
}//class
