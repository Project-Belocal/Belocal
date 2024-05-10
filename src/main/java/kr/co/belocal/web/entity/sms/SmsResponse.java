package kr.co.belocal.web.entity.sms;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Builder
public class SmsResponse {
    private String statusCode;
    private String statusName;
    private String requestId;
    private LocalDateTime requestTime;
}
