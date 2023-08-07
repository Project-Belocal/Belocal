package kr.co.belocal.web.controller.api;


import kr.co.belocal.web.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("apiAuthController")
@RequestMapping("api/register")
public class AuthController {

  private final AuthService authService;

  // 단일 생성자인 경우는 추가적인 어노테이션이 필요 없다.
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  //id 중복 확인
  @PostMapping("/id/validation")
  public ResponseEntity<?> validateId(@RequestBody Map<String, Object> req){
    String userId = (String) req.get("userId");

    String result = authService.isIdDuplicate(userId);


    //null이라면 중복값이 존해지않는다는 말
    if (result!=null)
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    return ResponseEntity.status(HttpStatus.OK).build();
  }


  @PostMapping("/nickname/validation")
  public ResponseEntity<?> validateNickname(@RequestBody Map<String, Object> req){
    String nickname = (String) req.get("userNickname");


    String result = authService.isNicknameDuplicate(nickname);

    if (result!=null)
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    return ResponseEntity.status(HttpStatus.OK).build();
  }




  @PostMapping()
  public void signUp(){

  }

  @GetMapping()
  public void logout(){

  }

}
