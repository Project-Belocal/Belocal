package kr.co.belocal.web.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TravelThemeView {
    private String path;
    private String uuid;
    private String nickName;
    private String title;
    private String description;
    private Integer isReserved;

}
