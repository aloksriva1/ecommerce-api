package com.corporate.learning.userservice.service;


import com.corporate.learning.userservice.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User createUser(User user);
    List<User> getAllUser();
    Optional<User> getUserById(UUID uuid);
    void deleteUser(UUID uuid);
    User updateUser(UUID uuid, User user);

}
