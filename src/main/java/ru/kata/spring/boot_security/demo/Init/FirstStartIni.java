package ru.kata.spring.boot_security.demo.Init;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dto.DtoUser;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class FirstStartIni {
    private final UserService userService;
    private final RoleService roleService;

    public FirstStartIni(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void createIfAbsent() {
        if (roleService.getListRoles().isEmpty()) {
            roleService.addRole(new Role("ROLE_ADMIN"));
            roleService.addRole(new Role("ROLE_USER"));
        }
        if (userService.getListUsers().isEmpty()) {
            DtoUser firstAdmin = new DtoUser("admin", "admin", Set.of("1"));
            userService.addUser(firstAdmin);
        }
    }
}
