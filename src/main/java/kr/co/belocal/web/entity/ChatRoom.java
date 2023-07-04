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
public class ChatRoom {
    private Integer id;                 //채팅방 번호
    private Integer travelThemeId;      //게시글 번호
    private Integer travelerId;         //여행객 번호
    private Integer guideId;            //가이드 번호
    private Integer isDeleted;          //삭제 유무
    private Integer isAccepted;         //수락 유무

}
