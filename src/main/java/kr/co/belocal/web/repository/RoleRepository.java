package kr.co.belocal.web.repository;

import org.apache.ibatis.annotations.Mapper;

import kr.co.belocal.web.entity.Role;

@Mapper
public interface RoleRepository {
    Role findByMemberId(Integer memberId);
}
