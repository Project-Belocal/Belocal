package kr.co.belocal.web.controller.api;


import kr.co.belocal.web.entity.Notice;
import kr.co.belocal.web.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {



    @GetMapping("/sub")
    public SseEmitter subscribe(
            @PathVariable Integer memberId,
            @RequestHeader(value = "Last-Event-ID", required = false,defaultValue = "") String lastEventId
    ){
        return new SseEmitter();
    }

}
