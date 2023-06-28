package kr.co.belocal.web.service;

import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.ProfileImage;
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
    private MemberRepository memberRepository;


    @Override
    public Member getById(Integer id) {

        return memberRepository.findById(id);
    }


    //회원정보 수정
    @Override
    public Integer editSave(Member member) {
        String defaultNickname = null;
        String defaultSelfIntroduction = null;
        String defaultPw = null;
        String defaultPhone = null;

        String nickname;
        String selfIntroduction;
        String pw;
        String phone;

        List<Member> list = authRepository.findAll(member.getId());
        for (Member defaultValue : list) {
            defaultNickname = defaultValue.getNickname();
            defaultSelfIntroduction = defaultValue.getSelfIntroduction();
            defaultPw = defaultValue.getPw();
            defaultPhone = defaultValue.getPhoneNum();
        }



        if (member.getNickname().equals("")||member.getNickname()==null)
            nickname = defaultNickname;
        else
            nickname = member.getNickname();


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
                .nickname(nickname)
                .selfIntroduction(selfIntroduction)
                .pw(pw)
                .phoneNum(phone)
                .build();


        Integer result = memberRepository.editSave(input);

        return result;
    }

    @Override
    public ProfileImage getProfileImg(Integer memberId) {
        return memberRepository.getProfileImg(memberId);
    }

}//class
