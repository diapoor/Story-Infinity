package com.example.TestProject.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.TestProject.dto.UserDTO;
import com.example.TestProject.entities.User;
import com.example.TestProject.service.JwtService;
import com.example.TestProject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //get All list users
    //role admin
    @GetMapping("/admin/list")
    public List<User> getall() {

        return userService.getAll();
    }

    // find User by username
    //role admin
    @GetMapping("/admin/search/{username}")
    public ResponseEntity<?> getOne(@PathVariable("username") String username) {
        User u = userService.getUserByUsername(username);
        u.setPassword(null);
        if(u == null) return ResponseEntity.status(404).body("User not found");
        return ResponseEntity.ok().body(u);
    }

    //details user(user)
    @GetMapping("/details")
    public ResponseEntity<?> detailsUser(HttpServletRequest request) {
        try {
            String token = JwtService.extractToken(request);
            String username = JwtService.extractInfoFromToken(token);
            User u = userService.getUserByUsername(username);
            u.setPassword(null);
            u.getRoles().clear();
            return ResponseEntity.ok().body(u);
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body("Some problem, Sorry.");
        }
    }

    @GetMapping("/admin/details/{username}")
    public ResponseEntity<?> detailsUserForAdmin(@PathVariable("username")String username) {
        try{
            User u = userService.getUserByUsername(username);
            if(u == null) return ResponseEntity.status(404).body("User not found");
            u.setPassword(null);
            return ResponseEntity.ok().body(u);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Some problem, Sorry");
        }
    }

    //Create user(for employee creation only)
    //role admin
    @PostMapping("/admin/createUser")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO obj, BindingResult result) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            } else {
                if (!obj.getUser().getPassword().equals(obj.getConfirmPassword()))
                    return ResponseEntity.badRequest().body("Password and ConfirmPassword not same");
                if(obj.getEmployeeId() == null)
                    return ResponseEntity.badRequest().body("This user is registered to the employee, so please enter the employee id");
                userService.save(obj);
                return ResponseEntity.ok("Register successfully");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Please enter the correct formats");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Can't create user: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("some problem");
        }
    }

    //Update User from user
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDTO obj, HttpServletRequest request, BindingResult result) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            } else {
                //only allowed to change the currently used account information
                String token = JwtService.extractToken(request);
                if (!obj.getUser().getPassword().equals(obj.getConfirmPassword()))
                    return ResponseEntity.badRequest().body("Password and ConfirmPassword not same");

                String username = JwtService.extractInfoFromToken(token);

                if(obj.getUser().getUsername().equals(username))
                    return  ResponseEntity.badRequest().body("Can't change username"); //only change the currently logged in account information

                userService.update(obj);
                return ResponseEntity.ok("updated");
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Can't update. ", HttpStatus.BAD_REQUEST);
        }
    }

    //update User from admin
    //admin
    @PutMapping("/admin/update")
    public ResponseEntity<?> updateUserAdmin(@RequestBody @Valid UserDTO obj,BindingResult result) {
        try {
            if (result.hasErrors()) {
                // get messages error from BindingResult
                List<String> errorMessages = result.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList());
                // return messages error
                return ResponseEntity.badRequest().body(errorMessages);
            }
            userService.update(obj);
            return ResponseEntity.ok("updated");
        } catch (Exception e) {
            return new ResponseEntity<>("Can't update: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //delete user
    //admin
    @DeleteMapping("/admin/delete/{username}")
    public ResponseEntity<?> deleteUserAdmin(@PathVariable("username")String username) {
        try{

            userService.deleteByUsername(username);
            return ResponseEntity.ok().body("deleted");
        }catch (DataAccessException e) {
            return ResponseEntity.badRequest().body("Can't delete this User.Please check information and role,or employee.");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getRole")
    public ResponseEntity<?> getRoleByUser(HttpServletRequest request) {
        try{
            String token = JwtService.extractToken(request);
            String username = JwtService.extractInfoFromToken(token);
            User user = userService.getUserByUsername(username);
            if(user == null) {
                return ResponseEntity.badRequest().body("Can't get role because user not found");
            }
            List<String> roleName = new ArrayList<>();
            user.getRoles().forEach(role -> {
                roleName.add(role.getName());
            });
            return ResponseEntity.ok().body(roleName);
        } catch (TokenExpiredException e) {
            return ResponseEntity.badRequest().body("Can't get role because token string is problem");
        }
    }
}
