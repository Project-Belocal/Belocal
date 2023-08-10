package kr.co.belocal.web.entity;


import java.sql.Timestamp;

public class Comment {
    private Integer id;
    private Integer postId;
    private Integer parentCommandId;
    private String content;
    private Timestamp regDate;
    private Timestamp modDate;
    private Integer isDeleted;
}
