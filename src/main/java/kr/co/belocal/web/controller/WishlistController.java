package kr.co.belocal.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.belocal.web.entity.Place;
import kr.co.belocal.web.entity.PlaceImage;
import kr.co.belocal.web.entity.TravelThemeView;
import kr.co.belocal.web.entity.Wishlist;
import kr.co.belocal.web.entity.WishlistGroup;
import kr.co.belocal.web.entity.WishlistGroupView;
import kr.co.belocal.web.service.PlaceImageService;
import kr.co.belocal.web.service.PlaceService;
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

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PlaceImageService placeImageService;

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
        Map<TravelThemeView, List<PlaceImage>> travelThemeMap = null;

        List<Integer> travelThemeIdList = wishlistService.getAllTravelThemeIdByGroupId(wishlistGroupId);
        if(!travelThemeIdList.isEmpty()) {

            List<TravelThemeView> travelThemeViewList = travelThemeService.getAllViewByIds(travelThemeIdList);
    
            List<List<Place>> placeList2d = new ArrayList<List<Place>>();
            for(Integer travelThemeId : travelThemeIdList) {
                List<Place> placeList = placeService.getListByTravelThemeId(travelThemeId);
                placeList2d.add(placeList);
            }
            
            List<List<PlaceImage>> placeImageList2d = new ArrayList<List<PlaceImage>>();
            for(int i = 0; i < placeList2d.size(); i++) {
                List<Place> placeList = placeList2d.get(i);
                List<PlaceImage> placeImageList = new ArrayList<PlaceImage>();
    
                for(int j = 0; j < placeList.size(); j++) {
                    Place place = placeList.get(j);
                    List<PlaceImage> tempPlaceImageList = placeImageService.getListByPlaceId(place.getId());
                    placeImageList.addAll(tempPlaceImageList);
                }
    
                placeImageList2d.add(i, placeImageList);
            }
            
            travelThemeMap = new LinkedHashMap<>();
            for(int i = 0; i < travelThemeViewList.size(); i++) {
                TravelThemeView travelThemeView = travelThemeViewList.get(i);
                List<PlaceImage> placeImageList = placeImageList2d.get(i);
    
                travelThemeMap.put(travelThemeView, placeImageList);
            }
    
            // for (Map.Entry<TravelThemeView, List<PlaceImage>> entry : travelThemeMap.entrySet()) {
            //     TravelThemeView travelThemeView = entry.getKey();
            //     List<PlaceImage> values = entry.getValue();
            //     System.out.println(travelThemeView);
            //     for(PlaceImage image : values) 
            //         System.out.println(image);
            // }
        }


        model.addAttribute("wishlistGroup", wishlistGroup);
        model.addAttribute("travelThemeMap", travelThemeMap);
        return "/wishlists/wish";
    }
}
