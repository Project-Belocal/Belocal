package kr.co.belocal.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.belocal.web.entity.WishlistGroup;
import kr.co.belocal.web.service.WishlistGroupService;

@RestController
@RequestMapping("api/wishlistGroups")
public class WishlistGroupController {
    
    @Autowired
    private WishlistGroupService service;

    @PostMapping
    public int add(@RequestBody WishlistGroup wishlistGroup) {
        int result = service.append(wishlistGroup);

        int wishlistGroupId = wishlistGroup.getId();
        return wishlistGroupId;
    }
}
