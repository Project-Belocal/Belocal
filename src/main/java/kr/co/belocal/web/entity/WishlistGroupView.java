package kr.co.belocal.web.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WishlistGroupView {
    private int id;
    private String title;
    private String path;
}
