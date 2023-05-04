package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRole(Long id) {
        return roleDao.getRole(id);
    }

    @Transactional
    @Override
    public void removeRole(Long id) {
        roleDao.removeRole(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getListRoles() {
        return roleDao.getListRoles();
    }

}
