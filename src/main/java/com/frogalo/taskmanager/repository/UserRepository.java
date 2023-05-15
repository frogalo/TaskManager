package com.frogalo.taskmanager.repository;

import com.frogalo.taskmanager.entity.Task;
import com.frogalo.taskmanager.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface UserRepository extends MongoRepository<User, String> {

    User getById(String id);

    // Additional method
    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    public void deleteById(String id);

    Set<User> findByTasksId(String taskId);

}
