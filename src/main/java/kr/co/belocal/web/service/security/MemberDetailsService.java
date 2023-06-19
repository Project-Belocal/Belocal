package kr.co.belocal.web.service.security;

import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.MemberRoleView;
import kr.co.belocal.web.exception.ErrorCode;
import kr.co.belocal.web.exception.NotFoundException;
import kr.co.belocal.web.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    private AuthService authService;

    //3OJwTDDz
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = authService.login(username);
        if (member==null){
            throw new NotFoundException(username, ErrorCode.MEMBER_NOT_FOUND);
        }

        MemberDetails memberDetails = MemberDetails
                .builder()
                .id(member.getId())
                .userId(member.getUserId())
                .pw(member.getPw())
                .profilePic(member.getProfilePic())
                .nickName(member.getNickName())
                .name(member.getName())
                .phoneNum(member.getPhoneNum())
                .rating(member.getRating())
                .build();


        List<GrantedAuthority> authorities = new ArrayList<>();
        List<MemberRoleView> role = authService.getMemberRole(memberDetails.getId());
        for (int i = 0; i < role.size(); i++) {
            authorities.add(new SimpleGrantedAuthority(role.get(i).getRole()));
        }

        memberDetails.setAuthorities(authorities);
        System.out.println("memberDetails = " + memberDetails);

        return memberDetails;
    }
}
