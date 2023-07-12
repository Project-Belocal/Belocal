package kr.co.belocal.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.belocal.web.entity.Notice;

@Mapper
public interface NoticeRepository {

    int save(Notice notice);
}
