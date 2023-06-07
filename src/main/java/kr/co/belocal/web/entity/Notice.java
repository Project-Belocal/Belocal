package kr.co.belocal.web.entity;

import java.sql.Timestamp;

public class Notice {
    private int id;
    private int memberId;
    private Integer isRead;
    private Timestamp createdDate;
    private String content;
}
