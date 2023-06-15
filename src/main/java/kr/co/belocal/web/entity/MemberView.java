package kr.co.belocal.web.entity;


import lombok.Data;

@Data
public class MemberView {
    private Integer memberId;
    private String userId;
    private String role;
}
