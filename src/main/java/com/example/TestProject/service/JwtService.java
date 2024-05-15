package com.example.TestProject.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.TestProject.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Date;

import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    //Generate Token from user
    public  String generateToken(User user, Collection<SimpleGrantedAuthority> authorities) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        return JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .withClaim("roles",authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }
    //Refresh Token
    public  String generateRefreshToken(User user, Collection<SimpleGrantedAuthority> authorities) {
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
        return JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration + expiration)) //one day
                .withClaim("roles",authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }
    //Get token from request
    public static String extractToken(HttpServletRequest request) {
        // Get the authorization header from the request
        String authorizationHeader = request.getHeader("Authorization");

        // Check if the authorization header is present and formatted correctly
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extract and return the token part
            return authorizationHeader.substring(7); // "Bearer " is 7 characters long
        }

        // Token not found or not in the expected format
        return null;
    }
    //get user from token
    public static String extractInfoFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
           String username = jwt.getSubject();//get username by token
           return username;
        } catch (JWTDecodeException e) {
            // Xử lý khi token không hợp lệ
            return "error: " + e.getMessage();
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }



}
