package kr.co.belocal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileImage {
    private Integer id;
    private Integer memberId; //상품?id
    private String name; //파일이름
    private String path; //경로
    private String uuid;

}
