package kr.co.belocal.web.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatLogListView {
    private Integer id; //채팅방id
    private String travelerImg;
    private Integer travelerId;
    private String guideImg;
    private Integer guideId;
    private String travelerNickname;
    private String guideNickname;
    private Integer memberId; //보낸사람 id
    private String message;
    private String regDate; //보낸시간
}
