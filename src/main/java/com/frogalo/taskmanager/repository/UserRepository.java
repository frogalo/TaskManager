package com.frogalo.taskmanager.repository;

import com.frogalo.taskmanager.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User getById(String id);

    // Additional method
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    public void deleteById(String id);
}
