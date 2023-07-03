package kr.co.belocal.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.belocal.web.entity.Role;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

@Mapper
public interface RoleRepository {
    Role findByMemberId(Integer memberId);
    List<Role> findRoleById(Integer memberId);
}
