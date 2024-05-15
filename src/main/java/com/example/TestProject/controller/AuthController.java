package com.example.TestProject.controller;

import com.example.TestProject.auth.AuthenticationRequest;
import com.example.TestProject.dto.UserDTO;
import com.example.TestProject.entities.User;
import com.example.TestProject.service.AuthenticationService;
import com.example.TestProject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//all public
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    private final UserService userService;

    //login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest authenticationRequest, BindingResult result) {
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
            return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(404).body("Username or password does not exists. ");
        }
    }

    //login to web admin
    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAdmin(@RequestBody @Valid AuthenticationRequest authenticationRequest, BindingResult result) {
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
            User user = userService.getUserByUsername(authenticationRequest.getUsername());
            if ( user== null ||user.getEmployee() == null ) {
                return ResponseEntity.badRequest().body("Username or Password incorrect");
            } else {
                return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("can't be process");
        }
    }

    // Register user
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO obj, BindingResult result) {
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
            if (!obj.getUser().getPassword().equals(obj.getConfirmPassword()))
                return ResponseEntity.badRequest().body("Password and ConfirmPassword not same");
            userService.save(obj);
            return ResponseEntity.ok("Register successfully");
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Please enter the correct formats");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("can't register: "+e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("some problem");
        }
    }

    //Password Recovery
    @PostMapping("/recoveryPassword")
    public ResponseEntity<?> recoveryPassword(@RequestBody @Valid UserDTO userDTO, BindingResult result) {
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
                if (!userDTO.getConfirmPassword().equals(userDTO.getUser().getPassword()))// check confirm password
                    return ResponseEntity.badRequest().body("Confirm the password must match the password");
                if (!userService.recoveryPassword(userDTO.getUser()))
                    return ResponseEntity.badRequest().body("Information User not same");
                return ResponseEntity.ok().body("let try login with new password");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("some problem: ");
        }
    }
}
