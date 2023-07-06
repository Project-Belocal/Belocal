package kr.co.belocal.web.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Category {
    private Integer id;
    private String name;
    private String icons;
}
