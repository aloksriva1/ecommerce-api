package com.corporate.learning.userservice.service;


import com.corporate.learning.userservice.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Mono<User> createUser(User user);
    Flux<User> getAllUsers();
    Mono<User> getUserById(UUID uuid);
    void deleteUser(UUID uuid);
    Mono<User> updateUser(UUID uuid, User user);


}
