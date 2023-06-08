package kr.co.belocal.web.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Theme {
    private Integer id;
    private Integer memberId;
    private String title;
    private String content;
    private String region;
    private Integer hits;
    private Date regDate;
    private Integer isDeleted;
    private Integer isReserved;
}
