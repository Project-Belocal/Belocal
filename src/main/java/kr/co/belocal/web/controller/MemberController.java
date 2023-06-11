package kr.co.belocal.web.controller;


import jakarta.servlet.http.HttpSession;
import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    @Autowired
    MemberServiceImpl memberService;

    //회원정보수정
    @GetMapping("my/profile-edit")
    public String profileEdit(){
        return "my/profile-edit";
    }
    //닉네임 중복 체크
    @PostMapping("sign-up/checkId")
    @ResponseBody
    public int CheckId(@RequestParam("id") String id, @RequestParam("type") String type){

        if (id.equals(memberService.checkId(id,type)))
            return 1;

        return 0;
    }



    //로그인페이지
    @GetMapping("login")
    public String login(){
        return "login";
    }

    //로그인 정보 전송
    @PostMapping("login")
    public String login(Member member , HttpSession session){


            Member login = memberService.login(member);

            if (login==null){
                System.out.println("가입된 정보 없음");
            }

            if(login!=null) {
                session.setAttribute("loginMember", login);
            }


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

        memberService.save(member);

        return "login";
    }

}
