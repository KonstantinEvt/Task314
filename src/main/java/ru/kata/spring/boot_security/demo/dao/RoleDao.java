package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {
    void addRole(Role role);

    void removeRole(Long id);

    Role getRole(Long id);

    Role getRoleByName(String role);

    List<Role> getListRoles();
}
