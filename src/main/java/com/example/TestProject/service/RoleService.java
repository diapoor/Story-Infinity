package com.example.TestProject.service;

import com.example.TestProject.entities.Role;
import com.example.TestProject.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService   {

    @Autowired
    private RoleRepository roleRepository;
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getOneById(int id) {
        Optional<Role> role = roleRepository.findById(id);
        Role result = null;
        if(role.isPresent()) {
            result = role.get();
        }
        return result;
    }
    public  Role findRoleByName(String name) {
        Optional<Role> role = roleRepository.findRoleByName(name);
        if(role.isPresent()) {
            Role result;
            result= role.get();
            return result;
        }
        return null;
    }

    public Role update(Role obj) {
        return null;
    }

    public Role save(Role obj) {
        return roleRepository.save(obj);
    }

    public void deleleById(int id) {
        Role role = getOneById(id);
        if(role == null) throw new RuntimeException("Role not found");
        if(roleRepository.getFirstUserByRole(id) != null) throw new RuntimeException("There is already a user using this role, please delete the user first");
        roleRepository.deleteById(id);
    }
}
