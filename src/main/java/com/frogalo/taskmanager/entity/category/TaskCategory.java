package com.frogalo.taskmanager.entity.category;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "categories")
public class TaskCategory extends Category {
    @Id
    private String taskId;
    private boolean isTaskCategory;

    public TaskCategory(String taskId, String name, String description, boolean isTaskCategory) {
        super(name, description);
        this.taskId = taskId;
        this.isTaskCategory = isTaskCategory;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public boolean isTaskCategory() {
        return isTaskCategory;
    }

    public void setTaskCategory(boolean taskCategory) {
        isTaskCategory = taskCategory;
    }

}
