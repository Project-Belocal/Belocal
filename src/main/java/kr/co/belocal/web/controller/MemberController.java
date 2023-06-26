package kr.co.belocal.web.controller;


import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.TravelTheme;
import kr.co.belocal.web.service.MemberService;
import kr.co.belocal.web.service.TravelThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TravelThemeService travelThemeService;

    @GetMapping("member-profile")
    public String getMemberProfile(@RequestParam(name = "i", required = false)Integer id, Model model){

        Member memberProfile = memberService.getById(id);
        List<TravelTheme> travelThemeList = travelThemeService.getListByMemberId(id);


        model.addAttribute("memberProfile", memberProfile);
        model.addAttribute("travelTheme", travelThemeList);


        return "member-profile";
    }
}//class










