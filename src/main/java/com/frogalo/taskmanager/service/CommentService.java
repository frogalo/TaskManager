package com.frogalo.taskmanager.service;

import com.frogalo.taskmanager.entity.*;
import com.frogalo.taskmanager.repository.CommentRepository;
import com.frogalo.taskmanager.repository.TaskRepository;
import com.frogalo.taskmanager.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    // method to return all comments
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    // method to return a comment by its ID
    public Comment addComment(Comment comment, String userId, String taskId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (userOptional.isPresent() && taskOptional.isPresent()) {
            User user = userOptional.get();
            Task task = taskOptional.get();

            if (comment instanceof IssueComment) {
                ((IssueComment) comment).setIssueCommentId(new ObjectId().toString());
                if (!task.getComments().contains(comment)) {
                    task.getComments().add(comment);
                }
            } else if (comment instanceof UpdateComment) {
                ((UpdateComment) comment).setUpdateCommentId(new ObjectId().toString());
                if (!task.getComments().contains(comment)) {
                    task.getComments().add(comment);
                }
            } else {
                throw new IllegalArgumentException("Invalid comment type");
            }

            if (!user.getComments().contains(comment)) {
                user.getComments().add(comment);
            }

            taskRepository.save(task);
            userRepository.save(user);
            return commentRepository.save(comment);
        } else {
            throw new IllegalArgumentException("User or task not found");
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

    public Comment getCommentById(String id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isPresent()) {
            return commentOptional.get();
        } else {
            throw new IllegalArgumentException("Comment not found");
        }
    }
}
