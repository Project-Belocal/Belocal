package kr.co.belocal.web.entity;

import java.sql.Timestamp;

public class Post {
    private Integer id;
    private Integer memberId;
    private String title;
    private String content;
    private Timestamp regDate;
    private Timestamp modDate;
    private Integer hits;
    private Integer isDeleted;
}
