package com.corporate.learning.userservice.service.impl;

import com.corporate.learning.userservice.entity.User;
import com.corporate.learning.userservice.repository.UserRepository;
import com.corporate.learning.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
       return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID uuid) {
        return userRepository.findById(uuid);
    }

    @Override
    public void deleteUser(UUID uuid) {
            userRepository.deleteById(uuid);
    }

    @Override
    public User updateUser(UUID uuid, User user) {
          User existingUser =  userRepository.findById(uuid).orElseThrow(()-> new RuntimeException("User not found with Id  : " + uuid));
//        existingUser.setId(uuid);
//        existingUser.setName(user.getName());
//        existingUser.setEmail(user.getEmail());
//        existingUser.setPassword(user.getPassword());
//        existingUser.setIsActive(user.getIsActive());
        return userRepository.save(existingUser);

      }

}

