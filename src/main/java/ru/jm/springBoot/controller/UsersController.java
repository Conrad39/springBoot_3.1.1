package ru.jm.springBoot.controller;
// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com


import javassist.NotFoundException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.jm.springBoot.model.Role;
import ru.jm.springBoot.model.User;
import ru.jm.springBoot.service.RoleService;
import ru.jm.springBoot.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UsersController {
    private final UserService userService;
    private final RoleService roleService;

    public UsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String index(Principal principal,ModelMap modelMap){
        modelMap.addAttribute("username", principal.getName());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("admin")
    public String listUser(ModelMap modelMap) {
        modelMap.addAttribute("list", userService.getAllUsers());
        return "adminPage";
    }

    @GetMapping("user")
    public String infoUser(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "userPage";
    }

    @GetMapping(value = "user/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "createNew";
    }

    @PostMapping(value = "user/new")
    public String newUser(@ModelAttribute User user,
                          @RequestParam(value = "rolez") String[] role) throws NotFoundException {
        Set<Role> rolesSet = new HashSet<>();
        for (String roles : role) {
            rolesSet.add(roleService.getByName(roles));
        }
        user.setRoles(rolesSet);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "user/edit/{id}")
    public String editUser(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "editUser";
    }

    @PostMapping(value = "user/edit/{id}")
    public String editUser(@ModelAttribute User user, @RequestParam(value = "rolez") String [] role) throws NotFoundException {
        Set<Role> rolesSet = new HashSet<>();
        for (String roles : role) {
            rolesSet.add((roleService.getByName(roles)));
        }
        user.setRoles(rolesSet);
        userService.edit(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "user/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/admin";
    }
}
