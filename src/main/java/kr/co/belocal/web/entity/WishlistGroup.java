package kr.co.belocal.web.entity;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishlistGroup {
    private Integer id;
    private Integer memberId;
    private String title;
    private Integer isDeleted;
    private Timestamp regDate;
}
