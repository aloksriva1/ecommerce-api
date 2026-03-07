package com.corporate.learning.auth_service.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @PostMapping("/login")
    public String login() {
        return "Login successful";
    }

    @PostMapping("/register")
    public String register() {
        return "User registered";
    }

    @GetMapping("/validate")
    public String validateToken() {
        return "Token valid";
    }
}