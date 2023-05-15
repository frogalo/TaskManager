package com.frogalo.taskmanager.repository;
import java.util.List;
import java.util.Set;

import com.frogalo.taskmanager.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {

    Task getById(String id);

    // Additional method
    Set<Task> findByProjectId(String projectId);
    public void deleteById(String id);

    Set<Task> findByUsersId(String userId);

//    Set<Task> getTasksByUserId(String userId);
}

