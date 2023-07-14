package kr.co.belocal.web.repository;

import kr.co.belocal.web.entity.Notice;
import kr.co.belocal.web.entity.NoticeView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeRepository {
    //채팅요청 저장
    int save(Notice notice);

    //채팅요청사항 조회
    List<Notice> getNoticeListById(Integer receiverId);

    List<NoticeView> getViewList(Integer receiverId);
}
