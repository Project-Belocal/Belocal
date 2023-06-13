package kr.co.belocal.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.belocal.web.entity.sms.SmsMessage;
import kr.co.belocal.web.entity.sms.SmsResponse;
import kr.co.belocal.web.service.sms.SmsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private final SmsService smsService;


    @PostMapping("/sms/send")
    public SmsResponse sendSms(@RequestBody Map<String ,Object> request) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, InvalidKeyException {
        return smsService.getPhoneNum(request);
    }
}
