package kr.co.belocal.web.entity;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TravelTheme {
    private int id;
    private int memberId;
    private String title;
    private String content;
    private String region;
    private int hits;
    private Timestamp regDate;
    private Integer isDeleted;
    private Integer isReserved;
}
