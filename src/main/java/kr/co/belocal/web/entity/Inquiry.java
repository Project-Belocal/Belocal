package kr.co.belocal.web.entity;


import java.sql.Timestamp;

public class Inquiry {
    private int id;
    private int memberId;
    private String title;
    private String content;
    private Timestamp regDate;
    private Integer isDeleted;
}
