package kr.co.belocal.web.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import kr.co.belocal.web.entity.Notice;

public interface NoticeService {

    int append(Notice notice);


}
