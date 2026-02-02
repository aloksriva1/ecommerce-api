package com.corporate.learning.userservice.service.impl;

import com.corporate.learning.userservice.entity.User;
import com.corporate.learning.userservice.repository.UserRepository;
import com.corporate.learning.userservice.service.UserService;
import com.github.f4b6a3.ulid.UlidCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final R2dbcEntityTemplate template;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(R2dbcEntityTemplate template)
    { this.template = template; }

    @Override
    public Mono<User> createUser(User user) {

        user.setId(UlidCreator.getUlid().toString());

        user.setIsActive(user.getIsActive());
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setCreationDate(user.getCreationDate());
        return template.insert(User.class).using(user);
      //  return userRepository.save(user);
    }

    @Cacheable(value = "usersCache", key = "#user.getAllUsers")
    public Flux<User> getAllUsers() {
       return (Flux<User>) userRepository.findAll();
    }

    @Override
    @Cacheable(value = "userCache", key = "#ulid")
    public Mono<User> getUserById(String ulid) {
        return userRepository.findById(ulid);
    }

    @Override
    public void deleteUser(String ulid) {
            userRepository.deleteById(ulid);
    }

    @Override
    @Cacheable(value = "userCache", key = "#ulid")
    public Mono<User> updateUser(String ulid, User user) {
        return userRepository.findById(ulid)
                .switchIfEmpty(Mono.error(
                        new RuntimeException("User not found with Id: " + ulid)
                ))
                .map(existingUser -> {
                  //  existingUser.setId(UUID.randomUUID().toString());
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPassword(user.getPassword());
                    existingUser.setIsActive(user.getIsActive());
                    return existingUser;
                })
                .flatMap(userRepository::save);
    }

}

