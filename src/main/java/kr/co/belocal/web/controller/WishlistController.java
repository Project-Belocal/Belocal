package kr.co.belocal.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.belocal.web.entity.TravelThemeView;
import kr.co.belocal.web.entity.Wishlist;
import kr.co.belocal.web.entity.WishlistGroup;
import kr.co.belocal.web.entity.WishlistGroupView;
import kr.co.belocal.web.service.TravelThemeService;
import kr.co.belocal.web.service.WishlistGroupService;
import kr.co.belocal.web.service.WishlistService;
import kr.co.belocal.web.service.security.MemberDetails;

@Controller
@RequestMapping("/wishlists")
public class WishlistController {

    @Autowired
    private WishlistGroupService wishlistGroupService;

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private TravelThemeService travelThemeService;

    @GetMapping
    public String list(
        Model model,
        @AuthenticationPrincipal MemberDetails member
    ) {
        int memberId = member.getId();
        List<WishlistGroupView> list = wishlistGroupService.getViewListByMemberId(memberId);

        model.addAttribute("wishlistGroupList", list);
        
        return "/wishlists/wishlists";
    }

    @GetMapping("/{wishlistGroupId}")
    public String detail(
        Model model,
        @PathVariable int wishlistGroupId,
        @AuthenticationPrincipal MemberDetails member
    ) {
        WishlistGroup wishlistGroup = wishlistGroupService.getById(wishlistGroupId);
        
        List<Integer> travelThemeIdList = wishlistService.getAllTravelThemeIdByGroupId(wishlistGroupId);
        List<TravelThemeView> travelThemeViewList = travelThemeService.getAllViewByIds(travelThemeIdList);

        model.addAttribute("travelThemeViewList", travelThemeViewList);
        model.addAttribute("wishlistGroup", wishlistGroup);
        return "/wishlists/wish";
    }
}
