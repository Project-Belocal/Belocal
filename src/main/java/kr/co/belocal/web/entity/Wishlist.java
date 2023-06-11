package kr.co.belocal.web.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Wishlist {
    private Integer id;
    private Integer memberId;
    private Integer travelThemeId;
}
