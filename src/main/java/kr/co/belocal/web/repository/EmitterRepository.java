package kr.co.belocal.web.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Map;

public interface EmitterRepository {
    //Emitter 저장
    SseEmitter save(Integer emitterId, SseEmitter sseEmitter);

//    //이벤트 저장
//    void saveEventCache(String eventCacheId, Object event);

    //해당 회원과 관련된 모든 Emitter를 찾는다
//    Map<Integer, SseEmitter> findAllEmitterStartWithById(Integer memberId);


    //해당 회원과관련된 모든 이벤트를 찾는다
    //Map<Integer, Object> findAllEventCacheStartWithById(Integer memberId);



    //Emitter를 지운다
    void deleteById(Integer memberId);

    //해당 회원과 관련된 모든 Emitter를 지운다
    void deleteAllEmitterStartWithId(Integer memberId);

    //해당 회원과 관련된 모든 이벤트를 지운다
//    void deleteAllEventCacheStartWithId(Integer memberId);
}
