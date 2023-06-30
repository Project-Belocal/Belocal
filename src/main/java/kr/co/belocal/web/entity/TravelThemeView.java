package kr.co.belocal.web.entity;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.sql.Timestamp;

@Data
@Builder
public class TravelThemeView {
    private Integer id;
    private Integer memberId;
    private String title;
    private String description;
    private Integer isReserved;
    private Timestamp regDate;
    private String nickname;
    private String uuid;
    private String path;
}
