package com.frogalo.taskmanager.service;

import com.frogalo.taskmanager.entity.Comment;
import com.frogalo.taskmanager.entity.IssueComment;
import com.frogalo.taskmanager.entity.Task;
import com.frogalo.taskmanager.entity.UpdateComment;
import com.frogalo.taskmanager.repository.CommentRepository;
import com.frogalo.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;


    public CommentService(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    // method to return all comments
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // method to return a comment by its ID
    public Comment getCommentById(String id) {
        return commentRepository.getById(id);
    }

    // method to add a new comment
    //fixme: do the same as in user-task classes
    public Comment addComment(Comment comment) {
        if (comment instanceof IssueComment || comment instanceof UpdateComment) {
            // Check if the task already has this comment
            Task task = comment.getTask();

            if (task != null && task.getComments().contains(comment)) {
                throw new IllegalArgumentException("The task already has this comment.");
            }
            Comment savedComment = commentRepository.save(comment);
            // Add the comment to the associated task
            if (task != null) {

                task.getComments().add(savedComment);
                taskRepository.save(task);
            }
            return savedComment;
        } else {
            throw new IllegalArgumentException("Invalid comment type");
        }
    }


    // method to delete a comment by its ID
    public void deleteComment(String id) {
        commentRepository.deleteById(id);
    }

    // method to update a comment by its ID
    public Comment updateComment(String id, Comment comment) {
        comment.setId(id);
        return commentRepository.save(comment);
    }

    // method to find comments by user
    public List<Comment> findCommentsByTaskId(String userId) {
        return commentRepository.findByTaskId(userId);
    }
}
