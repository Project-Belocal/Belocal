package kr.co.belocal.web.controller.api;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {


    //Sse 방식을 이용한 알림기능
    @GetMapping("/sub")
    public SseEmitter subscribe(
            @PathVariable Integer memberId,
            @RequestHeader(value = "Last-Event-ID", required = false,defaultValue = "") String lastEventId
    ){
        return new SseEmitter();
    }

}
