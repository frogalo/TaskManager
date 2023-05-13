package com.frogalo.taskmanager.service;

public class TaskAlreadyAssignedException extends Throwable {
    public TaskAlreadyAssignedException(String taskId, String userId) {
        super("Task " + taskId + " is already assigned to user " + userId);
    }
}
