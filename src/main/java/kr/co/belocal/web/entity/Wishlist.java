package kr.co.belocal.web.entity;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Wishlist {
    private Integer travelThemeId;
    private Integer wishlistGroupId;
    private Timestamp regDate;
}
