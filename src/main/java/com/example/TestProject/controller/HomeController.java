package com.example.TestProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class HomeController {
    @GetMapping("/test")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("successfuly");
    }
}
