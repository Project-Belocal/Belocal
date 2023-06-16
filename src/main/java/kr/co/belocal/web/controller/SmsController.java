package kr.co.belocal.web.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.belocal.web.service.sms.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class SmsController {

    private final SmsService smsService;


    //번호전송
    @PostMapping("/sms/send")
    public ResponseEntity<Void> sendSms(@RequestBody Map<String ,Object> toPhone) throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException, IllegalAccessException {

        String phoneNum = (String) toPhone.get("toPhone");

        smsService.sendSms(phoneNum);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PostMapping("/sms/verification")
    public ResponseEntity<Void> checkSMS(@RequestBody Map<String ,Object> requestData) {

        String phoneNum = (String) requestData.get("toPhone");
        String  verification = (String) requestData.get("verificationNum");

        boolean response = smsService.verifySms(phoneNum,verification);
        if (!response){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
