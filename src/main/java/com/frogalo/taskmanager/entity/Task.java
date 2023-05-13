package com.frogalo.taskmanager.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.ManyToMany;

import java.util.ArrayList;
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
    @ManyToMany(mappedBy = "tasks")
    private List<User> users = new ArrayList<>();
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

    public Task() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    public void addUser(User user) {
        if (this.users == null) {
            this.users = new ArrayList<>();
        }
        if (!this.users.contains(user)) {
            this.users.add(user);
            user.addTask(this);
        } else
            System.err.println("This user already has this task");
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", project=" + project +
                ", users=" + users +
                ", category=" + category +
                ", comments=" + comments +
                '}';
    }
}
