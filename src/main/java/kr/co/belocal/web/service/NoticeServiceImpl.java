package kr.co.belocal.web.service;


import kr.co.belocal.web.entity.Like;
import kr.co.belocal.web.repository.EmitterRepository;
import kr.co.belocal.web.repository.EmitterRepositoryImpl;
import kr.co.belocal.web.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import kr.co.belocal.web.entity.Notice;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private EmitterRepository emitterRepository;
    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public SseEmitter subscribe(Integer memberId, String lastEventId) throws IOException {
        SseEmitter emitter;
        emitter = emitterRepository.save(memberId,new SseEmitter(Long.MAX_VALUE));

        HashMap<String, Object> map = new HashMap<>();
        map.put("event","EventStream Created. [memberId=" + memberId + "]");


        sendNotification(emitter,
                lastEventId,
                String.valueOf(memberId),
                map);

        return emitter;
    }

    @Override
    public void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data) throws IOException {
        emitter.send(SseEmitter.event()
                .id(eventId)
                .name("sse")
                .data(data));
    }




//    @Override
//    public int append(Notice notice) {
//        return noticeRepository.save(notice);
//    }

}
