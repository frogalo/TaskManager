package com.frogalo.taskmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    private String content;
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;
    private String issueCommentId;
    private String updateCommentId;

    public Comment(String content, Date createdAt, User user, Task task, String issueCommentId, String updateCommentId) {
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
        this.task = task;
        if (issueCommentId != null)
            setIssueCommentId(issueCommentId);
        if (updateCommentId != null)
            setUpdateCommentId(updateCommentId);
    }

    public Comment() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getIssueCommentId() {
        return issueCommentId;
    }

    public void setIssueCommentId(String issueCommentId) {
        if (updateCommentId == null) {
            this.issueCommentId = issueCommentId;
        } else {
            throw new IllegalStateException("Cannot set IssueCommentId when UpdateCommentId is already set.");
        }
    }

    public String getUpdateCommentId() {
        return updateCommentId;
    }

    public void setUpdateCommentId(String updateCommentId) {
        if (issueCommentId == null) {
            this.updateCommentId = updateCommentId;
        } else {
            throw new IllegalStateException("Cannot set UpdateCommentId when IssueCommentId is already set.");
        }
    }


}

