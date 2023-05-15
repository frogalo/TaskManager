package com.frogalo.taskmanager.entity;

import java.util.Date;

public class UpdateComment extends Comment {
    private String updateStatus;
    private String updateDescription;

    public UpdateComment(String content, Date createdAt, User user, Task task, String updateCommentId, String updateStatus, String updateDescription) {
        super(content, createdAt, user, task, null, updateCommentId);
        this.updateStatus = updateStatus;
        this.updateDescription = updateDescription;
    }

    public UpdateComment() {
        super();
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getUpdateDescription() {
        return updateDescription;
    }

    public void setUpdateDescription(String updateDescription) {
        this.updateDescription = updateDescription;
    }
}
