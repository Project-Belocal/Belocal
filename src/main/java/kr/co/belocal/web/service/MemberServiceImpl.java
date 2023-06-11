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

    @Override
    public List<Member> getList() {
        List<Member> list = memberRepository.findAll(4);

        return list;
    }
}
