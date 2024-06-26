package kr.co.belocal.web.service;

import kr.co.belocal.web.entity.Role;

import java.util.List;

public interface RoleService {
    
    Integer getByMemberId(Integer memberId);

    List<Role> getRoleById(Integer memberId);
}
