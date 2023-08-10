package kr.co.belocal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class chatRequestListView {
        private Integer id;             //채팅방 id
        private Integer travelThemeId;  //게시글 id
        private Integer guideId;        //요청받은사람
        private Integer travelerId;     //요청보낸사람
        private String uuid;            //회원이미지
        private String nickname;        //회원닉네임
        private String title;           //글제목
        private String description;     //글내용
}
