package com.frogalo.taskmanager.service;

import com.frogalo.taskmanager.entity.Comment;
import com.frogalo.taskmanager.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
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
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
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
