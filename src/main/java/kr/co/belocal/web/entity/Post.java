package kr.co.belocal.web.entity;

import java.sql.Timestamp;

public class Post {
    private int id;
    private int memberId;
    private String title;
    private String content;
    private Timestamp regDate;
    private Timestamp modDate;
    private int hits;
    private Integer isDeleted;
}
