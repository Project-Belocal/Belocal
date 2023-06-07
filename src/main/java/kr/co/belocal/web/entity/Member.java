package kr.co.belocal.web.entity;

import java.sql.Date;
import java.math.BigDecimal;

public class Member {
    private int id;
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
