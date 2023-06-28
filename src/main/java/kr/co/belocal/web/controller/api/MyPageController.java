package kr.co.belocal.web.controller.api;

import kr.co.belocal.web.entity.ProfileImage;
import kr.co.belocal.web.service.AuthService;
import kr.co.belocal.web.service.FileService;
import kr.co.belocal.web.service.security.MemberDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController("apiMyPageController")
@RequestMapping("my/api/mys")
public class MyPageController {

    @Autowired
    private AuthService authService;

    @Autowired
    private FileService fileService;


    @PostMapping("/duplicateNickname")
    public int duplicateNickname(@RequestBody Map<String, Object> request) {


        String nickname = (String) request.get("nickname");

        if (nickname.equals(authService.isNicknameDuplicate(nickname)))
            return 1;
        return 0;
    }


    //0de951tJ
    @PostMapping("/currentPw")
    public int currentPw(@RequestBody Map<String, Object> request) {
        Integer id = Integer.valueOf((String) request.get("memberId"));
        String pw = (String) request.get("pw");

        System.out.println("id = " + id);
        System.out.println("pw = " + pw);

        Integer result = authService.checkPw(id, pw);
        if (result == 1)
            return 1;

        return 0;
    }

//    @PostMapping("/profileUpload")
//    public ResponseEntity<ProfileImage> profileUpload(
//            MultipartFile uploadFile,
//            @AuthenticationPrincipal MemberDetails user
//    ) throws IOException {
//
//        fileService.fileSave(uploadFile,memberId);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

}