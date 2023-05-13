package com.frogalo.taskmanager.repository;

import com.frogalo.taskmanager.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    Comment getById(String id);

    // Additional method
    List<Comment> findByTaskId(String taskId);
}
