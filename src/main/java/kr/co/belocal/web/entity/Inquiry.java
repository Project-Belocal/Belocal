package kr.co.belocal.web.entity;


import java.sql.Timestamp;

public class Inquiry {
    private Integer id;
    private Integer memberId;
    private String title;
    private String content;
    private Timestamp regDate;
    private Integer isDeleted;
}
