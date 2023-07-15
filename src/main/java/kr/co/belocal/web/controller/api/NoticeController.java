package kr.co.belocal.web.controller.api;

import jakarta.servlet.http.HttpServletResponse;
import kr.co.belocal.web.entity.Member;
import kr.co.belocal.web.entity.NoticeView;
import kr.co.belocal.web.service.ChatRoomService;
import kr.co.belocal.web.service.MemberService;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import kr.co.belocal.web.entity.Notice;
import kr.co.belocal.web.service.NoticeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private MemberService memberService;


//    // 클라이언트에서 구독을 하기 위한 subcribe
//    @GetMapping(value = "/subscribe/{memberId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public SseEmitter subscribe(@PathVariable String memberId,
//                                @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) throws IOException {
//
//        String id = memberId;
//        return noticeService.subscribe(id,lastEventId);
//    }
//
//    //서버에서 클라이언트로 알림 전달
//    @PostMapping("/send-data/{id}")
//    public void sendData(@PathVariable Integer id){
////        noticeService.notify(id,"data");
//    }



    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Notice notice) {
        int result = noticeService.append(notice);
        return new ResponseEntity<Object>(notice, HttpStatus.OK);
    }

    @PostMapping("/request")
    public void request(@RequestBody Map<String ,Object> req){
        String data = (String) req.get("status");
        Integer chatRoomId  = Integer.valueOf((String) req.get("roomId"));
        Integer senderId = Integer.valueOf((String) req.get("senderId"));

        System.out.println("senderId = " + senderId);
        System.out.println("chatRoomId = " + chatRoomId);

        if (data.equals("accept")){
            chatRoomService.isAccepte(1);
        }else {
            System.out.println("data = " + data);
        }


    }

}
