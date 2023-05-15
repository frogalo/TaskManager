package com.frogalo.taskmanager.entity.category;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class ProjectCategory extends Category {

    @Id
    private String projectId;
    private boolean isProjectCategory;

    public ProjectCategory(String projectId, String name, String description, boolean isProjectCategory) {
        super(name, description);
        this.projectId = projectId;
        this.isProjectCategory = isProjectCategory;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public boolean isProjectCategory() {
        return isProjectCategory;
    }

    public void setProjectCategory(boolean projectCategory) {
        isProjectCategory = projectCategory;
    }
}
