package kr.co.belocal.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.MemberRoleView;
import kr.co.belocal.web.entity.ProfileImage;
import kr.co.belocal.web.entity.Role;
import kr.co.belocal.web.exception.BusinessException;
import kr.co.belocal.web.exception.ErrorCode;
import kr.co.belocal.web.exception.NotFoundException;
import kr.co.belocal.web.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    //임시 비밀번호 생성
    private final String TemporaryPwd = temporaryKey();

    //아이디 찾기
    @Override
    public String findByUserId(String phoneNum) {
        String getId = authRepository.findByUserId(phoneNum);
        if (getId == null)
            throw new NotFoundException(phoneNum, ErrorCode.MEMBER_NOT_FOUND);

        //회원의 아이디를 일정부분 가려서 보여줌
        StringBuilder str = new StringBuilder(getId);
        for (int i = 2; i < 5; i++)
            str.setCharAt(i,'*');
        getId = str.toString();

        return getId;
    }

    //비밀번호 찾기
    @Override
    public String getFindPw(String userId, String phoneNum) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {

        //입력값과 일치하는게 있는지 파악
        Integer checkInfo = authRepository.getFindPw(userId,phoneNum);
        //일치하는게 없다면? notFound예외처리
        if (checkInfo==null){
            throw new NotFoundException(userId,ErrorCode.MEMBER_NOT_FOUND);
        }

        //일치한다면
        Member member = Member.builder()
                .id(checkInfo)
                .pw(passwordEncoder.encode(TemporaryPwd))
                .build();

        //임시 비밀번호로 변경
        authRepository.updateTemporaryPwd(member);

//        (입력값과 일치하는게 존재한다면?){
//            smsTemporaryPwd 임시 비밀번호를 찾은 유저 정보에 암호화 해서 업데이트한다
//            업데이트한 비밀번호를 sms서비스를 이용하여 입력받은 번호에 비밀번호를 전송
//            smsService.TemporarySms(phoneNum);
//            return Sucess
//        }
        return TemporaryPwd;
    }

    @Override
    public Integer checkPw(Integer memberId, String pw) {

        String  result = authRepository.checkPw(memberId, pw);

        if (result==null)
            throw new NotFoundException(pw,ErrorCode.INVALID_INPUT_VALUE);

        if (passwordEncoder.matches(pw,result))
            return 1;
        return 0;
    }


    @Override
    public Member login(String username) {
        return authRepository.login(username);
    }

    @Override
    public List<MemberRoleView> getMemberRole(Integer memberId) {
        return authRepository.getMemberRole(memberId);
    }


    //회원가입
    @Override
    public int save(Member member) {
        member.setPw(passwordEncoder.encode(member.getPw()));
        int id = authRepository.save(member);

        return id;
    }

    @Override
    public Integer FindByRole(Integer memberId) {
        return authRepository.findByRole(memberId);
    }

    //회원가입시 권한 부여
    @Override
    public void addRole(int id) {
        authRepository.addRole(id);
    }

    @Override
    public void addGuideRole(Integer memberId) {
        Role role = Role.builder()
                .memberId(memberId)
                .roleTypeId(3)
                .build();


        Integer result = authRepository.findByRole(memberId);
        if (result!=null){
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }


        authRepository.addGuideRole(role);
    }


    //아이디 중복 확인
    @Override
    public String isIdDuplicate(String userId) {
        return authRepository.isIdDuplicate(userId);
    }

    //닉네임 중복 확인
    @Override
    public String isNicknameDuplicate(String nickname) {
        return authRepository.isNicknameDuplicate(nickname);
    }

    //휴대폰 중복 확인
    @Override
    public String  isPhoneNumDuplicate(String phoneNum) {
        return authRepository.isPhoneNumDuplicate(phoneNum);
    }


    //회원가입시 기본이미지 추가
    @Override
    public void addDefaultImg(Integer memberId) {
        ProfileImage profileImage = ProfileImage
                .builder()
                .memberId(memberId)
                .name("null.jpg")
                .path("belocal-bucket")
                .uuid("user.svg")
                .build();

        authRepository.addDefaultImg(profileImage);
    }


    // 임시 비밀번호 메서드
    public String temporaryKey() {
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
}
