package kr.co.belocal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeView {
    private Integer receiverId;
    private Integer senderId;
    private String nickname;
    private Integer chatRoomId;
    private String createdDate;
    private Integer isAccepted;
}
