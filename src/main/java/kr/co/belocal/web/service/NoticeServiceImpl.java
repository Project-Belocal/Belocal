package kr.co.belocal.web.service;



import kr.co.belocal.web.entity.NoticeView;
import kr.co.belocal.web.repository.NoticeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.belocal.web.entity.Notice;

import java.util.List;


@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public int append(Notice notice) {
        return noticeRepository.save(notice);
    }

    @Override
    public List<Notice> getNoticeListById(Integer memberId) {
        return noticeRepository.getNoticeListById(memberId);
    }

    @Override
    public List<NoticeView> getViewList(Integer receiverId) {
        return noticeRepository.getViewList(receiverId);
    }

    @Override
    public void deleted(Integer chatRoomId) {
        noticeRepository.delete(chatRoomId);
    }

    @Override
    public Integer getCount(Integer memberId) {
        return noticeRepository.getCount(memberId);
    }
}


