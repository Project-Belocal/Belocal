package kr.co.belocal.web.entity;

import java.sql.Timestamp;

public class Notice {
    private Integer id;
    private Integer memberId;
    private Integer isRead;
    private Timestamp createdDate;
    private String content;
}
