package kr.co.belocal.web.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
public class TravelTheme {
    private Integer id;
    private Integer memberId;
    private String title;
    private String description;
    private String bookableDateStart;
    private String bookableDateEnd;
    private String contactPreferredTimeStart;
    private String contactPreferredTimeEnd;
    private Integer hits;
    private Timestamp regDate;
    private Integer isDeleted;
    private Integer isReserved;
}
