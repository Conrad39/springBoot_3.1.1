package ru.jm.springBoot.service;
// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

import javassist.NotFoundException;
import ru.jm.springBoot.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void save(User user);

    void delete(User user);

    void edit(User user);

    User getById(long id);

    User getByName(String userName) throws NotFoundException;
}
