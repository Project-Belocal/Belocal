package kr.co.belocal.web.repository;

import kr.co.belocal.web.entity.Member;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void findAll() {
        List<Member> list = memberRepository.findAll(4);
        System.out.println("list = " + list);
    }

    @Test
    void findId() {
        String id = memberRepository.findId("01012345678");
        System.out.println("id = " + id);
    }

    @Test
    void findPw() {
        String pw = memberRepository.findPw("kkk","01012345678");
        System.out.println("pw = " + pw);
    }

    @Test
    void save() {
        String birth = "1993-02-18";
        Member member = Member
                .builder()
                .userId("ral")
                .pw("qwerasdf")
                .nickName("중복닉")
                .name("김찬호")
                .phoneNum("01078945612")
                .birthDate(Date.valueOf(birth))
                .build();

        int result = memberRepository.save(member);
    }

    @Test
    void update() {
        Member member = Member
                .builder()
                .id(4)
                .profilePic(null)
                .pw("qwerasdfzxc1")
                .nickName("엄준식")
                .phoneNum("01079879812")
                .build();

        int result = memberRepository.update(member);
    }

    @Test
    void delete() {
        Member member = Member
                .builder()
                .id(4)
                .isWithdrawl(1)
                .build();

        int result = memberRepository.delete(member);
    }
}