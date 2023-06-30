package kr.co.belocal.web.repository;

import kr.co.belocal.web.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.relational.core.sql.In;


@Mapper
public interface RoleRepository {
    Role findByMemberId(Integer id);
}
