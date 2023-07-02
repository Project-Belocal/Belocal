package kr.co.belocal.web.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceView {
    private Integer id;
    private Integer travelThemeId;
    private String category;
    private String location;
    private String description;
    private Integer order;
    private String path;
}
