package com.frogalo.taskmanager.repository;
import java.util.List;
import com.frogalo.taskmanager.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {

    Task getById(String id);

    // Additional method
    List<Task> findByProjectId(String projectId);
    public void deleteById(String id);
}

