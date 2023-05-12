package com.frogalo.taskmanager.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    private String content;
    private Date createdAt;
    @DBRef
    private User user;
    @DBRef
    private Task task;

    public Comment(String content, Date createdAt, User user, Task task) {
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
        this.task = task;
    }

    // getters and setters
}

