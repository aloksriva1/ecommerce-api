package com.corporate.learning.userservice.controller;

import com.corporate.learning.userservice.entity.User;
import com.corporate.learning.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

@Autowired
    UserService userService;

    @GetMapping("/user")
    public List<User> getUser() {
     return (List<User>) userService.getUser();
    }

}
