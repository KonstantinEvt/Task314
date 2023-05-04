package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.dto.DtoUser;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(DtoUser dtoUser);

    User getUser(Long id);

    void removeUser(Long id);

    List<User> getListUsers();

    void updateUser(DtoUser dtoUser, Long id);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User findUserByUsername(String username);
}
