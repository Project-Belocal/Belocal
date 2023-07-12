package kr.co.belocal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    private Integer id;
    private Integer senderId;
    private Integer receiverId;
    private Integer chatRoomId;
    private Integer isRead;
    private String createdDate;
    private String content;
    private Integer type;
}
