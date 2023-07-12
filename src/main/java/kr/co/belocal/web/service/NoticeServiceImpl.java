package kr.co.belocal.web.service;


import kr.co.belocal.web.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;


public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private NoticeRepository repository;

}
