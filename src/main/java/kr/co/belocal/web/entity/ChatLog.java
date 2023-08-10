package kr.co.belocal.web.entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatLog {
    private Integer id;             //채팅내역(등록글) 번호
    private Integer chatRoomId;     //채팅방(게시판) 번호
    private Integer memberId;       //회원(글등록자) 번호
    private String message;         //메시지(등록글)
    private String regDate;         //등록시간
    private Integer isChecked;      //확인여부
}
