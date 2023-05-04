package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.DtoUser;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String getAllUsers(@ModelAttribute("useradd") DtoUser dtoUser, ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(authentication.getName());
        model.addAttribute("admin", user);
        model.addAttribute("usersList", userService.getListUsers());
        model.addAttribute("userRoles", roleService.getListRoles());
        return "userData";
    }

    @PostMapping("/persist")
    public String addUser(DtoUser dtoUser) {
        userService.addUser(dtoUser);
        return "redirect:/admin";
    }

    @PatchMapping("/update")
    public String updateUser(DtoUser dtoUser) {
        userService.updateUser(dtoUser, dtoUser.getId());
        return "redirect:/admin";
    }

    @DeleteMapping("/remove")
    public String removeUser(Long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }
}
