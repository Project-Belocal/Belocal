package kr.co.belocal.web.service.sms;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import kr.co.belocal.web.config.SmsCertification;
import kr.co.belocal.web.entity.sms.SmsMessage;
import kr.co.belocal.web.entity.sms.SmsRequest;
import kr.co.belocal.web.entity.sms.SmsResponse;
import kr.co.belocal.web.exception.AuthenticationNumberMismatchException;
import kr.co.belocal.web.exception.BusinessException;
import kr.co.belocal.web.exception.ErrorCode;
import kr.co.belocal.web.exception.PhoneNumberDuplicateException;
import kr.co.belocal.web.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@PropertySource("classpath:application.properties")
@Slf4j
@RequiredArgsConstructor
@Service
public class SmsService {
    //sms정보
    @Value("${naver-cloud-sms.accessKey}")
    private String accessKey;
    @Value("${naver-cloud-sms.secretKey}")
    private String secretKey;
    @Value("${naver-cloud-sms.serviceId}")
    private String serviceId;
    @Value("${naver-cloud-sms.senderPhone}")
    private String phone;

    private final AuthService authService;
    private final SmsCertification smsCertification;


    //휴대폰 인증 번호 생성
    private final String smsConfirmNum = createSmsKey();



    //전달 데이터 암호화
    public String makeSignature(String time) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        String space = " ";          // one space
        String newLine = "\n";          // new line
        String method = "POST";          // method
        String url = "/sms/v2/services/" + this.serviceId + "/messages";  // url (include query string)
//        String timestamp = time.toString();			// current timestamp (epoch)
        String accessKey = this.accessKey;
        String secretKey = this.secretKey;

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(time)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }

    //인증번호 전송
    public void sendSms(String phoneNumber) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String  duplicatePhone = authService.duplicatePhoneNum(phoneNumber);
        if (duplicatePhone!=null) {
            throw new PhoneNumberDuplicateException(phoneNumber);
        } else if (duplicatePhone == null) {


            //난수 생성
            String smsConfirmNum = createSmsKey();
            //현재시간
            String time = Long.toString(System.currentTimeMillis());

            List<SmsMessage> messages = new ArrayList<>();
            // 보내는 사람에게 내용을 보냄.
            SmsMessage smsMessage = new SmsMessage();
            smsMessage.setTo(phoneNumber);
            messages.add(smsMessage); // 번호생성후 전송

            // 전체 json에 대해 메시지를 만든다.
            SmsRequest smsRequest = SmsRequest.builder()
                    .type("SMS")
                    .contentType("COMM")
                    .countryCode("82")
                    .from(phone)
                    .content("[Belocal] 인증번호 [" + smsConfirmNum + "]를 입력해주세요")
                    .messages(messages)
                    .build();

            // 쌓아온 바디를 json 형태로 변환시켜준다.
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(smsRequest);

            // 헤더세팅
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-ncp-apigw-timestamp", time);
            headers.set("x-ncp-iam-access-key", accessKey);
            // signature 서명하기.
            headers.set("x-ncp-apigw-signature-v2", makeSignature(time)); // signature 서명


            // 위에서 조립한 jsonBody와 헤더를 조립한다.
            HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);
//        System.out.println(body.getBody());

            //restTemplate로 post 요청 보내고 오류가 없으면 202코드 반환
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
                //restTemplate를 통해 외부 api와 통신

                SmsResponse smsResponse = restTemplate.postForObject(
                        new URI("https://sens.apigw.ntruss.com/sms/v2/services/" + serviceId + "/messages"),
                        body,
                        SmsResponse.class);
            } catch (Exception e) {
                throw new BusinessException(ErrorCode.SMS_API_CALL_FAILED);
            }


            smsCertification.createSmsCertification(phoneNumber, smsConfirmNum);
        }
    }


    //임시 비밀번호 전송
    public void TemporarySms(String phoneNumber,String TemporaryPwd) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        String  duplicatePhone = authService.duplicatePhoneNum(phoneNumber);
        if (duplicatePhone==null) {
            throw new PhoneNumberDuplicateException(phoneNumber);
        } else if (duplicatePhone != null) {



            //현재시간
            String time = Long.toString(System.currentTimeMillis());

            List<SmsMessage> messages = new ArrayList<>();
            // 보내는 사람에게 내용을 보냄.
            SmsMessage smsMessage = new SmsMessage();
            smsMessage.setTo(phoneNumber);
            messages.add(smsMessage); // 번호생성후 전송

            // 전체 json에 대해 메시지를 만든다.
            SmsRequest smsRequest = SmsRequest.builder()
                    .type("SMS")
                    .contentType("COMM")
                    .countryCode("82")
                    .from(phone)
                    .content("[Belocal] 임시비밀번호는 [" + TemporaryPwd + "]입니다. 로그인후 비밀번호를 변경해주세요.")
                    .messages(messages)
                    .build();

            // 쌓아온 바디를 json 형태로 변환시켜준다.
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonBody = objectMapper.writeValueAsString(smsRequest);

            // 헤더세팅
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-ncp-apigw-timestamp", time);
            headers.set("x-ncp-iam-access-key", accessKey);
            // signature 서명하기.
            headers.set("x-ncp-apigw-signature-v2", makeSignature(time)); // signature 서명


            // 위에서 조립한 jsonBody와 헤더를 조립한다.
            HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);
//        System.out.println(body.getBody());

            //restTemplate로 post 요청 보내고 오류가 없으면 202코드 반환
            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
                //restTemplate를 통해 외부 api와 통신

                SmsResponse smsResponse = restTemplate.postForObject(
                        new URI("https://sens.apigw.ntruss.com/sms/v2/services/" + serviceId + "/messages"),
                        body,
                        SmsResponse.class);
            } catch (Exception e) {
                throw new BusinessException(ErrorCode.SMS_API_CALL_FAILED);
            }
        }
    }


    //사용자가 입력한 인증번호가 Redis에 저장된 인증번호와 동일한지 확인
    public boolean verifySms(String phone, String verification) {
        if (isVerify(phone,verification)) {
            try {
                throw new AuthenticationNumberMismatchException("인증번호가 일치하지 않습니다.");
            } catch (AuthenticationNumberMismatchException e) {
                e.printStackTrace();
            }
            return false;
        }
        smsCertification.removeSmsCertification(phone);
        return true;
    }

    //폰번호 전달해서 값 존재 및 일치 확인
    private boolean isVerify(String phone,String verification) {
        return !(smsCertification.hasKey(phone) && //해당 번호에 인증키값이 존재하는지 확인
                smsCertification.getSmsCertification(phone).equals(verification));//입력된 폰번호에 해당하는 인증키 반환
    }





    // 인증코드 만들기
    public String createSmsKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 5; i++) { // 인증코드 5자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }



}



