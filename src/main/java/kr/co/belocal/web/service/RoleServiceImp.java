package kr.co.belocal.web.service;

import kr.co.belocal.web.entity.Role;
import kr.co.belocal.web.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    private RoleRepository repository;


    @Override
    public Role getByMemberId(Integer id) {

        Role test = repository.findByMemberId(id);

        System.out.println("id:" + id );
        System.out.print("test:"+ test);

        return test;
    }
}
