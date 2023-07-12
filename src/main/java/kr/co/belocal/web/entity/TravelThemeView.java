package kr.co.belocal.web.entity;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

import java.sql.Timestamp;

@Data
@Builder
public class TravelThemeView {
    private Integer id; //travel_theme
    private String title;  //travel_theme
    private String description;  //travel_theme
    private Integer isReserved;  //travel_theme
    private Integer memberId;  //travel_theme
    private Timestamp regDate; //travel_theme
    private String nickname; //member
    private String uuid; //profile_image
    private String path; //place
    private Integer ctgId; //category
    private String name; //category
    private Integer lcId; //location
    private String lcName; //location
}
