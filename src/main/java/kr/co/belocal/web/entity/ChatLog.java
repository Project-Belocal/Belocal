package kr.co.belocal.web.entity;



import java.sql.Timestamp;

public class ChatLog {
    private int id;
    private int chatRoomId;
    private int memberId;
    private String message;
    private Timestamp regDate;
    private Integer isChecked;
}
