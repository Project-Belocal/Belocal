package kr.co.belocal.web.service.security;

import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.MemberRoleView;
import kr.co.belocal.web.entity.ProfileImage;
import kr.co.belocal.web.exception.ErrorCode;
import kr.co.belocal.web.exception.NotFoundException;
import kr.co.belocal.web.repository.MemberRepository;
import kr.co.belocal.web.service.AuthService;
import kr.co.belocal.web.service.FileService;
import kr.co.belocal.web.service.MemberService;
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

    @Autowired
    private MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("username = " + username);

        Member member = authService.login(username);
        if (member==null){
            throw new NotFoundException(username, ErrorCode.MEMBER_NOT_FOUND);
        }


        ProfileImage profileImage = memberService.getProfileImg(member.getId());
        StringBuilder img = new StringBuilder();


        if (profileImage.getName().isEmpty()){
            img.append("/images/icon/user.svg");
        }else {
            img.append("https://storage.googleapis.com/")
                    .append(profileImage.getPath()+"/")
                    .append(profileImage.getUuid());
        }




//        String img = "https://storage.googleapis.com/belocal-bucket/"+profileImage.getUuid();

        MemberDetails memberDetails = MemberDetails
                .builder()
                .id(member.getId())
                .userId(member.getUserId())
                .pw(member.getPw())
                .nickname(member.getNickname())
                .name(member.getName())
                .phoneNum(member.getPhoneNum())
                .profileImg(String.valueOf(img))
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
