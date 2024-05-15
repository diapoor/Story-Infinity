package com.example.TestProject.service;

import com.example.TestProject.auth.AuthenticationRequest;
import com.example.TestProject.auth.AuthenticationResponse;
import com.example.TestProject.entities.Role;
import com.example.TestProject.entities.User;
import com.example.TestProject.repositories.RoleRepository;
import com.example.TestProject.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    //authentication.
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {//1.Receiving Authentication Request
        //2.AuthenticationManager: is used to authenticate the user's credentials (username and password) using Spring Security's authentication manager.
        // If the provided credentials are invalid, it may throw an AuthenticationException.
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
        //3.Fetching User from Database:After successful authentication, it retrieves the user from the database using the provided username.
        User user = userRepository.findUserByUsername(authenticationRequest.getUsername()).orElseThrow();
        //4.Fetching Roles for the User: Using the retrieved user, it fetches associated roles for that user from the database
        Set<Role> roles = user.getRoles();
        //5.Preparing Authorities: converts the roles to Spring Security's SimpleGrantedAuthority objects. These authorities represent the roles that the user has.
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.stream().forEach(i->authorities.add(new SimpleGrantedAuthority(i.getName())));
        //6.Generating JWT Token:
        var jwtToken = jwtService.generateToken(user,authorities);
        var jwtRefreshToken = jwtService.generateRefreshToken(user,authorities);
        //7.Building and Returning Authentication Response:
        return  AuthenticationResponse.builder().token(jwtToken).refreshToken(jwtRefreshToken).build();
    }

}
