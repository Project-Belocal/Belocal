package kr.co.belocal.web.service;



import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
public interface NoticeService {

    SseEmitter subscribe(Integer memberId, String lastEventId) throws IOException;
    void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data) throws IOException;
//    int append(Notice notice);


}
