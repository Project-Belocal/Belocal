package kr.co.belocal.web.service;

import kr.co.belocal.web.entity.Role;

public interface RoleService {
    Role getByMemberId(Integer id);
}
