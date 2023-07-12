package kr.co.belocal.web.service;


import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
import kr.co.belocal.web.entity.Notice;

public interface NoticeService {

    int append(Notice notice);


}
