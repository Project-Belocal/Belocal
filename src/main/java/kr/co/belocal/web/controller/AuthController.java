package kr.co.belocal.web.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.service.AuthService;
import kr.co.belocal.web.service.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {
//admin1  qwer1234#


    @Autowired
    private AuthService authService;

    @Autowired
    private SmsService smsService;


    //아이디 중복 확인
    @PostMapping("sign-up/checkId")
    @ResponseBody
    public int duplicateId(@RequestParam("id") String id){

        System.out.println("id = " + id);
        if (id.equals(authService.duplicateId(id)))
            return 1;
        return 0;
    }

    //닉네임 중복 확인
    @PostMapping("sign-up/checkNickName")
    @ResponseBody
    public int CheckNickName(@RequestParam("nickName") String nickName){

        if (nickName.equals(authService.duplicateNickName(nickName)))
            return 1;
        return 0;
    }


    //로그인페이지
    @GetMapping("login")
    public String login(){
        return "login/login";
    }

    //로그인 정보 전송
    @PostMapping("login")
    public String  login(Member member , HttpSession session){
        //로그인 실패
        if (!authService.login(member))
            return "redirect:/login?error";

        //로그인에 성공한다면
        return "redirect:/";
    }

    //회원가입 페이지
    @GetMapping("/sign-up")
    public String join(){
        return "sign-up";
    }

    //회원가입 정보 전송
    @PostMapping("/sign-up")
    public String join(Member member,HttpSession session){
        authService.save(member);
        authService.addRole(member.getId());
        return "login/login";
    }

    //아이디 찾기
    @GetMapping("/login/find-id")
    public String findId(){
        return "login/find-id";
    }

    //비밀번호 찾기
    @GetMapping("/login/find-pw")
    public String findPw(){
        return "login/find-pw";
    }



    @PostMapping("/login/find-id/check")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Map<String ,Object>  findId(@RequestBody Map<String ,Object> request){
        String response = (String) request.get("phoneNum");
        String userId = authService.getFindId(response);

        Map<String ,Object> returnMap = new HashMap<>();
        returnMap.put("userId",userId);

        return returnMap;
    }

//    @PostMapping("/login/find-pw/check")
//    public void findPw(String userId,String phoneNum){
//        authService.getFindPw(userId, phoneNum);
//    }


    //임시 비밀번호 전송
    @PostMapping("/login/find-pw/check")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public String  pwdSms(@RequestBody Map<String ,Object> request) throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException, IllegalAccessException {

        String phoneNum = (String) request.get("toPhone");
        String userId = (String) request.get("userId");


        String TemporaryPwd = authService.getFindPw(userId,phoneNum);

        System.out.println("TemporaryPwd = " + TemporaryPwd);
        //일치한다면 임시비밀번호로 정보 변경후 문자 전송
        smsService.TemporarySms(phoneNum,TemporaryPwd);

//        if (getfindpw가 실패한다면) 실패전송

        return "OK";
    }

}
