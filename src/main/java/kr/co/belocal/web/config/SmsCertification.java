package kr.co.belocal.web.config;


import kr.co.belocal.web.entity.sms.SmsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@RequiredArgsConstructor
@Repository
public class SmsCertification {

    private final String PREFIX = "sms:";  // Redis에 저장되는 Key값이 중복되지 않도록 상수 선언
    private final int LIMIT_TIME = 3 * 60;  // Redis에서 해당 데이터의 유효시간(TTL)을 설정

    private final StringRedisTemplate stringRedisTemplate;


    //상태코드 반환?
//    public SmsResponse responseStatus(SmsResponse smsResponse){
//        return smsResponse;
//    }


    //사용자가 입력한 휴대폰 번호와 인증번호를 저장하고 TTL을 180초로 설정
    public void createSmsCertification(String phone, String certificationNumber) {
        System.out.println("phone = " + phone);
        System.out.println("certificationNumber = " + certificationNumber);

        stringRedisTemplate.opsForValue()
                .set(PREFIX + phone, certificationNumber, Duration.ofSeconds(LIMIT_TIME));
    }

    //Redis에서 휴대폰번호(KEY)에 해당하는 인증번호를 리턴
    public String getSmsCertification(String phone) {
        System.out.println("getSmsCertification = " + phone);
        return stringRedisTemplate.opsForValue().get(PREFIX + phone);
    }

    //Redis에 해당 휴대폰번호(KEY)로 저장된 인증번호(VALUE)가 존재하는지 확인
    public boolean hasKey(String phone) {
        System.out.println("hasKey = " + phone);
        return stringRedisTemplate.hasKey(PREFIX + phone);
    }

    //인증이 완료되었을 경우 메모리 관리를 위해 Redis에 저장된 인증번호 삭제
    public void removeSmsCertification(String phone) {
        System.out.println("removeSmsCertification"+phone);
        stringRedisTemplate.delete(PREFIX + phone);
    }
}
