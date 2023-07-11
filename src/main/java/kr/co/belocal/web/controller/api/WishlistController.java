package kr.co.belocal.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.belocal.web.entity.Wishlist;
import kr.co.belocal.web.service.WishlistService;

@RestController("apiWishlistController")
@RequestMapping("api/wishlists")
public class WishlistController {
    
    @Autowired
    private WishlistService service;

    @DeleteMapping("/{travelThemeId}/members/{memberId}")
    public int delete(
        @PathVariable("travelThemeId") int travelThemeId,
        @PathVariable("memberId") int memberId
    ) {

        return service.delete(travelThemeId, memberId);
    }

    @GetMapping("/count")
    public int count(@RequestParam("tid") int travelThemeId) {
        
        return service.getCountsByTravelTheme(travelThemeId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Wishlist wishlist) {
        // System.out.println(wishlist);
        int result = service.append(wishlist);
        if(result == 1)
            return new ResponseEntity<Object> (wishlist, HttpStatus.OK);
        return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
    }
}
