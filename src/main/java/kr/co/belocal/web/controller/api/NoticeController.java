package kr.co.belocal.web.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import kr.co.belocal.web.entity.Notice;
import kr.co.belocal.web.service.NoticeService;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    @Autowired
    private NoticeService service;

    //Sse 방식을 이용한 알림기능
    @GetMapping("/sub")
    public SseEmitter subscribe(
            @PathVariable Integer memberId,
            @RequestHeader(value = "Last-Event-ID", required = false,defaultValue = "") String lastEventId
    ){
        return new SseEmitter();
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Notice notice) {
        int result = service.append(notice);
        return new ResponseEntity<Object>(notice, HttpStatus.OK);
    }
}
