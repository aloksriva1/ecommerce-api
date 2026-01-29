package com.corporate.learning.userservice.service.impl;

import com.corporate.learning.userservice.entity.User;
import com.corporate.learning.userservice.repository.UserRepository;
import com.corporate.learning.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Mono<User> createUser(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setIsActive(user.getIsActive());
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setCreationDate(user.getCreationDate());
        return userRepository.save(user);
    }

    public Flux<User> getAllUsers() {
       return (Flux<User>) userRepository.findAll();
    }

    @Override
    public Mono<User> getUserById(UUID uuid) {
        return userRepository.findById(uuid);
    }

    @Override
    public void deleteUser(UUID uuid) {
            userRepository.deleteById(uuid);
    }

    @Override
    public Mono<User> updateUser(UUID uuid, User user) {
        return userRepository.findById(uuid)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("User not found with Id: " + uuid)
                ))
                .map(existingUser -> {
                    existingUser.setId(UUID.randomUUID().toString());
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPassword(user.getPassword());
                    existingUser.setIsActive(user.getIsActive());
                    return existingUser;
                })
                .flatMap(userRepository::save);
    }

}

