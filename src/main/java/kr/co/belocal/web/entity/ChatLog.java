package kr.co.belocal.web.entity;



import java.sql.Timestamp;

public class ChatLog {
    private Integer id;
    private Integer chatRoomId;
    private Integer memberId;
    private String message;
    private Timestamp regDate;
    private Integer isChecked;
}
