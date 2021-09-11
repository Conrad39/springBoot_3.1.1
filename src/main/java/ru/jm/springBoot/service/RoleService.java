package ru.jm.springBoot.service;
// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

import javassist.NotFoundException;
import ru.jm.springBoot.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    void add(Role role);
    void edit(Role role);
    Role getById(long id);
    Role getByName(String name) throws NotFoundException;
}
