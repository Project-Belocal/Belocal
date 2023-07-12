package kr.co.belocal.web.repository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@NoArgsConstructor
@Slf4j
public class EmitterRepositoryImpl implements EmitterRepository{

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();


    //Emitter 저장
    @Override
    public SseEmitter save(String emitterId, SseEmitter sseEmitter) {
        emitters.put(emitterId, sseEmitter);
        log.info("emitter {}",emitters);
        return sseEmitter;
    }

    //이벤트 저장
    @Override
    public void saveEventCache(String eventCacheId, Object event) {

    }

    //해당 회원과 관련된 모든 Emitter를 찾는다
    @Override
    public Map<Integer, SseEmitter> findAllEmitterStartWithById(Integer memberId) {
        return null;
    }

    //Emitter를 지운다
    @Override
    public void deleteById(Integer memberId) {

    }

    //해당 회원과 관련된 모든 Emitter를 지운다
    @Override
    public void deleteAllEmitterStartWithId(Integer memberId) {

    }


}
