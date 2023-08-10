package kr.co.belocal.web.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Place {
    private Integer id;
    private Integer travelThemeId;
    private Integer categoryId;
    private Integer locationId;
    private String description;
    private Integer order;
}
