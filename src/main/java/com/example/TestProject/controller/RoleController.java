package com.example.TestProject.controller;

import com.example.TestProject.entities.Role;
import com.example.TestProject.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    //get all role
    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(roleService.getAll());
    }

    //details
    @GetMapping("/{roleId}")
    public ResponseEntity<?> detailsRole(@PathVariable("roleId")int roleId) {
        Role role = roleService.getOneById(roleId);
        if(role == null) return  ResponseEntity.status(404).body("Role not found");
        return ResponseEntity.ok().body(role);
    }
    //add role
    @PostMapping("/add")
    public ResponseEntity<?> addRole(@RequestBody @Valid Role role, BindingResult result) {
        if (result.hasErrors()) {
            // get messages error from BindingResult
            List<String> errorMessages = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            // return messages error
            return ResponseEntity.badRequest().body(errorMessages);
        }
        roleService.save(role);
        return ResponseEntity.ok().body("Create Role successfully");
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRole(@RequestBody @Valid Role role,BindingResult result) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            }
            if(role.getId() < 1) return ResponseEntity.status(404).body("Role not found");
            roleService.deleleById(role.getId());
            return new ResponseEntity<>("Delete Successfully",HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Can't delete this role: "+e.getMessage());
        }
    }
}
