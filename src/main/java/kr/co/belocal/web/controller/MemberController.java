package kr.co.belocal.web.controller;

import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.Role;
import kr.co.belocal.web.entity.TravelTheme;
import kr.co.belocal.web.entity.TravelThemeView;
import kr.co.belocal.web.service.MemberService;
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



    @GetMapping("member-profile")
    public String getMemberProfile(
            @RequestParam(name = "i", required = false, defaultValue="2")Integer id,
            @RequestParam(name = "offset", defaultValue = "0") int offset, Model model){

        Member memberProfile = memberService.getById(id);
        List<TravelThemeView> travelThemeList = travelThemeService.getListByMemberId(id, offset);
        Role role =roleService.getByMemberId(id);

        System.out.println("role:" +role);


        model.addAttribute("memberProfile", memberProfile);
        model.addAttribute("travelThemeList", travelThemeList);
        model.addAttribute("role", role);



        return "member-profile";
    }
}//class










