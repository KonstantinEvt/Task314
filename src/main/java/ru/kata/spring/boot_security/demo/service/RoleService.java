package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    void addRole(Role role);

    Role getRole(Long id);

    void removeRole(Long id);

    List<Role> getListRoles();


}
