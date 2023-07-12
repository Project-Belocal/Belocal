package kr.co.belocal.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import kr.co.belocal.web.repository.EmitterRepository;
import kr.co.belocal.web.service.NoticeService;
import kr.co.belocal.web.service.security.MemberDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import kr.co.belocal.web.entity.Notice;
import kr.co.belocal.web.service.NoticeService;


@Slf4j
@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    @Autowired
    private final NoticeService noticeService;
    private final EmitterRepository emitterRepository;


    // 클라이언트에서 구독을 하기 위한 subcribe
    @GetMapping(value = "/subscribe/{memberId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable Integer memberId,
                                @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId)
    {

        return emitterRepository.subscribe(memberId,lastEventId);
    }

    //서버에서 클라이언트로 알림 전달
    @PostMapping("/send-data/{id}")
    public void sendData(@PathVariable Integer id){
//        noticeService.notify(id,"data");
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Notice notice) {
        int result = service.append(notice);
        return new ResponseEntity<Object>(notice, HttpStatus.OK);
    }
}
