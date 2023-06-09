package kr.co.belocal.web.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.math.BigDecimal;
import java.util.Arrays;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Integer id;
    private String userId;
    private String pw;
    private byte[] profilePic;
    private String nickName;
    private String name;
    private String phoneNum;
    private Date birthDate;
    private Date regDate;
    private BigDecimal rating;
    private Integer isWithdrawl;

}
