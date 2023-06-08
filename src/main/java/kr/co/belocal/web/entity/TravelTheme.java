package kr.co.belocal.web.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;


@Data
@Builder
public class TravelTheme {
    private Integer id;
    private Integer memberId;
    private String title;
    private String content;
    private String region;
    private Integer hits;
    private Timestamp regDate;
    private Integer isDeleted;
    private Integer isReserved;
}
