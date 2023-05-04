package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.dto.DtoUser;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.UserSecurityDetails;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;
    private BCryptPasswordEncoder encoder;

    public UserServiceImp(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder encoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.encoder = encoder;
    }

    @Transactional
    @Override
    public void addUser(DtoUser dtoUser) {
        try {
            userDao.findUserByUsername(dtoUser.getUsername());
            System.out.println("This user is already exist");
        } catch (UsernameNotFoundException e) {
            userDao.addUser(convertDtoToEntity(dtoUser));
        }
    }

    private User convertDtoToEntity(DtoUser dtoUser) {
        User userNew = new User();
        userNew.setFirstName(dtoUser.getFirstName());
        userNew.setLastName(dtoUser.getLastName());
        userNew.setAge(dtoUser.getAge());
        userNew.setEmail(dtoUser.getEmail());
        userNew.setUsername(dtoUser.getUsername());
        Set<Role> userRole = new HashSet<>();
        for (String role : dtoUser.getRoles()) {
            userRole.add(roleDao.getRole(Long.parseLong(role)));
        }
        userNew.setRoles(userRole);
        if (!Objects.equals(dtoUser.getPassword(), "")) {
            userNew.setPassword(encoder.encode(dtoUser.getPassword()));
        }
        return userNew;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Transactional
    @Override
    public void removeUser(Long id) {
        userDao.removeUser(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getListUsers() {
        return userDao.getListUsers();
    }

    @Transactional
    @Override
    public void updateUser(DtoUser dtoUser, Long id) {
        userDao.updateUser(convertDtoToEntity(dtoUser), id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserSecurityDetails userSecurityDetails = new UserSecurityDetails(userDao.findUserByUsername(username));
            return new org.springframework.security.core.userdetails.User(userSecurityDetails.getUsername(), userSecurityDetails.getPassword(), userSecurityDetails.getAuthorities());
        } catch (Exception e) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
    }

    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }
}
