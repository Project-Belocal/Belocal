package kr.co.belocal.web.entity;


import java.sql.Timestamp;

public class Announcement {
    private Integer id;
    private String title;
    private String content;
    private Timestamp regDate;
    private Integer isDeleted;
    private Integer memberId;
}
