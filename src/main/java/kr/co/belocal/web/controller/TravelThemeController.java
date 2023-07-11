package kr.co.belocal.web.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.Place;
import kr.co.belocal.web.entity.PlaceImage;
import kr.co.belocal.web.entity.PlaceView;
import kr.co.belocal.web.entity.ProfileImage;
import kr.co.belocal.web.entity.Role;
import kr.co.belocal.web.entity.TravelTheme;
import kr.co.belocal.web.entity.WishlistGroup;
import kr.co.belocal.web.entity.WishlistGroupView;
import kr.co.belocal.web.service.MemberService;
import kr.co.belocal.web.service.PlaceImageService;
import kr.co.belocal.web.service.PlaceService;
import kr.co.belocal.web.service.ProfileImageService;
import kr.co.belocal.web.service.RoleService;
import kr.co.belocal.web.service.TravelThemeService;
import kr.co.belocal.web.service.WishlistGroupService;
import kr.co.belocal.web.service.WishlistService;
import kr.co.belocal.web.service.security.MemberDetails;

@Controller
@RequestMapping("/theme")
public class TravelThemeController {

    @Autowired
    private TravelThemeService travelThemeService;

    @Autowired 
    private PlaceService placeService;

    @Autowired
    private PlaceImageService placeImageService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private WishlistGroupService wishlistGroupService;

    @Autowired
    private ProfileImageService profileImageService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/theme-list")
    public String list(Model model) {

        List<TravelTheme> list = travelThemeService.getList();
        model.addAttribute("list", list);
        System.out.println(list);
        return "theme/theme-list";
    }


    // 리다이렉트 두 번 일어나는듯..?
    @GetMapping("theme-detail")
    public String detail(
        @RequestParam(name = "id", required = true) Integer travelThemeId,
        @AuthenticationPrincipal MemberDetails member,        
        Model model
    ) {
        // 테마 상세 페이지에서 thymeleaf로 출력할 데이터 준비
        TravelTheme travelTheme = travelThemeService.get(travelThemeId);
        List<PlaceView> placeViewList = placeService.getViewListByTravelThemeId(travelThemeId);
        List<List<PlaceImage>> placeImageLists2d = new ArrayList<List<PlaceImage>>();
        List<PlaceImage> placeImageLists1d = new ArrayList<>();

        for(int i = 0; i < placeViewList.size(); i++) {
            int placeId = placeViewList.get(i).getId();
            List<PlaceImage> placeImageList = placeImageService.getListByPlaceId(placeId);
            placeImageLists2d.add(i, placeImageList);
            placeImageLists1d.addAll(placeImageList);
        }

        int uploadMemberId = travelTheme.getMemberId();
        Member uploadMember = memberService.getById(uploadMemberId); 
        int wishlistCount = wishlistService.getCountsByTravelTheme(travelThemeId);
        
        ProfileImage profileImage = profileImageService.getByMemberId(uploadMemberId);

        int isAlreadyOnWishlist = 0;
        List<WishlistGroupView> wishlistGroupViewList = null;
        if(member != null) {
            int memberId = member.getId();
            isAlreadyOnWishlist = wishlistGroupService.getStatus(travelThemeId, memberId);
            wishlistGroupViewList = wishlistGroupService.getViewListByMemberId(memberId);
        }
             
        

        // Role role = roleService.getByMemberId(memberId);
        // System.out.println(role);
        model.addAttribute("travelTheme", travelTheme);
        model.addAttribute("placeList", placeViewList);
        model.addAttribute("placeImageLists2d", placeImageLists2d);
        model.addAttribute("placeImageLists1d", placeImageLists1d);
        model.addAttribute("member", uploadMember);
        model.addAttribute("wishlistCount", wishlistCount);
        model.addAttribute("isAlreadyOnWishlist", isAlreadyOnWishlist);
        model.addAttribute("wishlistGroupViewList", wishlistGroupViewList);
        model.addAttribute("profileImage", profileImage);
        return "theme/theme-detail";
    }
}

