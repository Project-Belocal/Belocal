package kr.co.belocal.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.belocal.web.entity.Role;
import kr.co.belocal.web.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    private RoleRepository repository;

    @Override
    public Role getByMemberId(Integer memberId) {
        return repository.findByMemberId(memberId);
    }


    @Override
    public List<Role> getRoleById(Integer memberId) {
        return repository.findRoleById(memberId);
    }
    
}
