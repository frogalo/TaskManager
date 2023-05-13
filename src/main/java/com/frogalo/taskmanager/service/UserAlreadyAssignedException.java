package com.frogalo.taskmanager.service;

public class UserAlreadyAssignedException extends Exception {
    private String userId;
    private String taskId;

    public UserAlreadyAssignedException(String userId, String taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    @Override
    public String getMessage() {
        return "User " + userId + " is already assigned to task " + taskId;
    }
}
