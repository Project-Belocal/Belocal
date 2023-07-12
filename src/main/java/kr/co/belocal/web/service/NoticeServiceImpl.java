package kr.co.belocal.web.service;


import kr.co.belocal.web.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.Notice;
import kr.co.belocal.web.repository.NoticeRepository;

@Service
public class NoticeServiceImpl implements NoticeService{


    @Autowired
    private NoticeRepository repository;

    @Override
    public int append(Notice notice) {
        return repository.save(notice);
    }

}
