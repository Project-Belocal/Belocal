package kr.co.belocal.web.controller;

import kr.co.belocal.web.entity.*;
import kr.co.belocal.web.service.MemberService;
import kr.co.belocal.web.service.ProfileImageService;
import kr.co.belocal.web.service.RoleService;
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
    @Autowired
    private RoleService roleService;
    @Autowired
    private ProfileImageService profileImageService;



    @GetMapping("member-profile")
    public String getMemberProfile(
            @RequestParam(name = "i", required = false) Integer id,
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            Integer memberId,
            Model model){

        Member memberProfile = memberService.getById(id);

        int size = 6;
        List<TravelThemeView> travelThemeList = travelThemeService.getListByMemberId(id, offset, size);

        //-- member의 역할 표시해주는 기능(0:관리자, 1:일반회원, 2:가이드)
        Integer role = roleService.getByMemberId(id);

//        ProfileImage profileImage = profileImageService.getByMemberId(id);
        ProfileImage profileImage = profileImageService.getByMemberId(id);



        model.addAttribute("memberProfile", memberProfile);
        model.addAttribute("travelThemeList", travelThemeList);
        model.addAttribute("role", role);
        model.addAttribute("memberId", id);
//        model.addAttribute("roleType", roleType);
        model.addAttribute("profileImage", profileImage);


        System.out.println("role: " + role);



        return "member-profile";
    }
}//class










