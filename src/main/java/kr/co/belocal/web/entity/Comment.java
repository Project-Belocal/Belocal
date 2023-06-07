package kr.co.belocal.web.entity;


import java.sql.Timestamp;

public class Comment {
    private int id;
    private int postId;
    private int parentCommandId;
    private String content;
    private Timestamp regDate;
    private Timestamp modDate;
    private Integer isDeleted;
}
