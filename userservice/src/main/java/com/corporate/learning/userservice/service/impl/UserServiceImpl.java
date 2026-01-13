package com.corporate.learning.userservice.service.impl;

import com.corporate.learning.userservice.entity.User;
import com.corporate.learning.userservice.repository.UserRepository;
import com.corporate.learning.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return  null;
    }

    public List<User> getUser() {
       return (List<User>) userRepository.findAll();
    }
}
