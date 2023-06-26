package kr.co.belocal.web.entity;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Builder;
import lombok.Data;


import java.sql.Date;
import java.math.BigDecimal;



@Data
@Builder
public class Member {
    private Integer id;
    private String userId;
    private String pw;
    private String nickname;
    private String name;
    private String phoneNum;
    private Date birthDate;
    private Date regDate;
    private BigDecimal rating;
    private Integer isWithdrawl;
    private String selfIntroduction;
}
