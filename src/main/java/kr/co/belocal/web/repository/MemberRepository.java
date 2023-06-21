package kr.co.belocal.web.repository;

import kr.co.belocal.web.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {

    Member findById(Integer id);
}
