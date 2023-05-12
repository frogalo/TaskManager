package com.frogalo.taskmanager.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String status;
    @DBRef
    private Project project;
    @DBRef
    private List<User> users;
    @DBRef
    private Category category;
    @DBRef
    private List<Comment> comments;

    public Task(String name, String description, Date startDate, Date endDate, String status,
                Project project, List<User> users, Category category, List<Comment> comments) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.project = project;
        this.users = users;
        this.category = category;
        this.comments = comments;
    }

    // getters and setters
}
