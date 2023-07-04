package kr.co.belocal.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jakarta.servlet.http.HttpSession;
import kr.co.belocal.web.entity.*;
import kr.co.belocal.web.service.*;
import kr.co.belocal.web.service.security.MemberDetails;
import kr.co.belocal.web.service.security.MemberDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.belocal.web.controller.request.UploadPlaceImageRequest;
import kr.co.belocal.web.controller.request.UploadRequest;

@Controller
@RequestMapping("/my")
public class MyPageController {

    @Value("${upload.path}")
    private String uploadPath;


    @Autowired
    private MemberDetailsService memberDetailsService;



    @Autowired
    private FileService fileService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private TravelThemeService travelThemeService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PlaceImageService placeImageService;



    @GetMapping
    public String profile() {
        return "/member/my/profile";
    }


    @GetMapping("/profile-edit")
    public String profileEdit(){
        return "member/my/profile-edit";
    }


    @PostMapping("/profile-edit/send")
    public String profileEdit(Member member,
                              @RequestParam("uploadFile") MultipartFile uploadFile,
                              HttpSession session
    ) throws IOException {


        fileService.profileSave(uploadFile,member.getId()); //회원 이미지 저장
        memberService.editSave(member); // 회원정보 수정


        //이미지를 업데이트 후 시큐리티 세션을 재등록 해주는 작업업
        UserDetails user = memberDetailsService.loadUserByUsername(member.getUserId());
        Authentication auth = new UsernamePasswordAuthenticationToken
                (user,user.getPassword(),user.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.getContext();

        securityContext.setAuthentication(auth);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);



        return "redirect:/my";
    }







    @GetMapping("/theme-register")
    public String themeRegister() {
        return "/member/theme-register";
    }


    // @PostMapping("/upload")
    // public String upload(@RequestBody UploadRequest uploadRequest) {
    //     System.out.println(uploadRequest);
    //     return "redirect:/";
    // }

    @PostMapping("/upload-file") 
    public ResponseEntity<String[]> uploadFile(
            @RequestPart(name="image") MultipartFile[] fileList,
            HttpServletRequest request
            ) throws IllegalStateException, IOException {

        String[] filePathList = new String[fileList.length];    
        String filePath = System.getProperty("user.dir");
        System.out.println(filePath); 
        // for(MultipartFile image: fileList) {
            
        //     // 파일명 생성 
        //     // System.out.println(file.getOriginalFilename());
        //     // jpg로만 해도 되나???
        //     String fileName = UUID.randomUUID().toString() + ".jpg";

        //     // 파일 경로 생성
        //     // String filePath = uploadPath + File.separator + fileName;
            
        //     // 파일 저장
        //     File file = new File(uploadPath, fileName);
        //     image.transferTo(file);
        // }
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/upload-theme")
    public ResponseEntity<Integer> uploadTravelTheme(@RequestBody TravelTheme travelTheme) {
        travelTheme.setMemberId(2);
        int result = travelThemeService.save(travelTheme);

        return ResponseEntity.ok().body(travelTheme.getId());
    }

    @PostMapping("/upload-place")
    public ResponseEntity<Integer> uploadPlace(@RequestBody Place place) {
        int result = placeService.append(place);

        return ResponseEntity.ok().body(place.getId());
    }

    @PostMapping("/upload-img")
    public String uploadImage(@RequestBody UploadPlaceImageRequest requestBody) {
        List<List<PlaceImage>> placesImages = requestBody.placesImages();
        
        for(int i = 0 ; i < placesImages.size(); i++) {
            List<PlaceImage> list = placesImages.get(i);

            for(int j = 0; j < list.size(); j++) 
                placeImageService.append(list.get(j));
        }

        // String url = "redirect:/theme/theme-detail";\
        
        String travelThemeId = String.valueOf(requestBody.travelThemeId());
        String url = "redirect:/theme/theme-detail?id=" + travelThemeId;
        return url;
    }
}
