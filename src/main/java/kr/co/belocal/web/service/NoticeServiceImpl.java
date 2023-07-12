package kr.co.belocal.web.service;

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
