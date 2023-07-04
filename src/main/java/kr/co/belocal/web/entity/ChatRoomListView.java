package kr.co.belocal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomListView {
    private Integer id;              //채팅방 id
    private Integer isAccepted;      //채팅방 수락여부
    private Integer travelerId;      // 여행객 id
    private Integer guideId;         // 가이드 id
    private String path;             // img 경로
    private String travelerUuid;     // 여행객 img
    private String guideUuid;        // 가이드 img
    private String travelerNickname; //여행객 닉네임
    private String guideNickname;    // 가이드 닉네임
    private String message;          // 마지막으로 받은 메세지
    private String regDate;          // 마지막으로 받은 메시지 시간
    private Integer checkedCount;    // 안읽은 메시지 개수
}
