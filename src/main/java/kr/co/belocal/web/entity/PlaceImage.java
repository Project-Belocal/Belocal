package kr.co.belocal.web.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlaceImage {
    private Integer id;
    private Integer placeId;
    private String path;
    private Integer order;
}
