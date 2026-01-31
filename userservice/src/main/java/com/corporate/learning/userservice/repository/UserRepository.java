package com.corporate.learning.userservice.repository;



import com.corporate.learning.userservice.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;



public interface UserRepository extends ReactiveCrudRepository<User, String> {
}
