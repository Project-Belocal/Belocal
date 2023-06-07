package kr.co.belocal.web.entity;


import java.sql.Timestamp;

public class Announcement {
    private int id;
    private String title;
    private String content;
    private Timestamp regDate;
    private Integer isDeleted;
    private int memberId;
}
