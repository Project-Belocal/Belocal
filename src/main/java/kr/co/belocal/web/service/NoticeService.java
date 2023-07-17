package kr.co.belocal.web.service;



import kr.co.belocal.web.entity.Notice;
import kr.co.belocal.web.entity.NoticeView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoticeService {
    //채팅요청 보내기
    int append(Notice notice);

    List<Notice> getNoticeListById(Integer memberId);

    List<NoticeView> getViewList(Integer receiverId);

    void deleted(Integer chatRoomId);
}
