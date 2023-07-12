package kr.co.belocal.web.entity;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Notice {
    private Integer id;
    private Integer senderId;
    private Integer receiverId;
    private Integer chatRoomId;
    private Integer isRead;
    private Timestamp createdDate;
    private String content;
    private Integer type;
}
