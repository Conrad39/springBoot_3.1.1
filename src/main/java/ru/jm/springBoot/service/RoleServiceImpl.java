package ru.jm.springBoot.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jm.springBoot.dao.RoleRepository;
import ru.jm.springBoot.model.Role;

import java.util.List;
import java.util.Optional;

// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com
@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void add(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void edit(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getById(long id) {
        Role role = null;
        Optional<Role> optional = roleRepository.findById(id);
        if (optional.isPresent()) {
            role = optional.get();
        }
        return role;

    }

    @Override
    public Role getByName(String name) throws NotFoundException {
        Role role = roleRepository.findByName(name);
        if (role == null){
            throw new NotFoundException(name);
        }
        return role;
    }
}
