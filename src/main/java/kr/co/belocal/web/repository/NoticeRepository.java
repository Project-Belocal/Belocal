package kr.co.belocal.web.repository;

import kr.co.belocal.web.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Mapper
public interface NoticeRepository {


    int save(Notice notice);
}
