package com.corporate.learning.userservice.controller;

import com.corporate.learning.userservice.entity.User;
import com.corporate.learning.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    // 1. Create User
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Test endpoint
    @GetMapping("/test")
    public Mono<String> testData() {
        return Mono.just("User Service is up and running");
    }

    // 2. Get All Users
    @GetMapping("/getAllUsers")
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 3. Get User By ID
    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
