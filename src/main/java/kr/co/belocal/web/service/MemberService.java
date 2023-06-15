package kr.co.belocal.web.service;


import kr.co.belocal.web.entity.Member;


public interface MemberService {
    String getListByFindId(String phoneNum);
    String getListByFindId(String userId,String phoneNum);
    void save(Member member);
    boolean login(Member member);



    String checkId(String userId);
    String checkNickName(String nickName);

}//interface
