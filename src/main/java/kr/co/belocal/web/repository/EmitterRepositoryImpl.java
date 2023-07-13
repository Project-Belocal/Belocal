package kr.co.belocal.web.repository;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@NoArgsConstructor
@Slf4j
public class EmitterRepositoryImpl implements EmitterRepository{

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();


    //Emitter 저장
    @Override
    public SseEmitter save(Integer emitterId, SseEmitter sseEmitter) {
        emitters.put(String.valueOf(emitterId), sseEmitter);
        log.info("emitter {}",emitters);

        return sseEmitter;
    }


    //해당 회원과 관련된 모든 Emitter를 찾는다
//    @Override
//    public Map<Integer, SseEmitter> findAllEmitterStartWithById(Integer memberId) {
////        return emitters.entrySet().stream() //여러개의 Emitter가 존재할 수 있기떄문에 stream 사용
////                .filter(entry -> entry.getKey().startsWith(String.valueOf(memberId)))
////                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    }

    //Emitter를 지운다
    @Override
    public void deleteById(Integer memberId) {

    }

    //해당 회원과 관련된 모든 Emitter를 지운다
    @Override
    public void deleteAllEmitterStartWithId(Integer memberId) {

    }


}
