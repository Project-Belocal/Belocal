package kr.co.belocal.web.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Location {
    private Integer id;
    private String name;    
}
