package com.corporate.learning.userservice.service;


import com.corporate.learning.userservice.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    List<User> getUser();
}
