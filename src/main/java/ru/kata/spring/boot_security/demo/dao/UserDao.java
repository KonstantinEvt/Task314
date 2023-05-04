package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    void removeUser(Long id);

    User getUser(Long id);

    List<User> getListUsers();

    void updateUser(User user, Long id);

    User findUserByUsername(String loginName);
}
