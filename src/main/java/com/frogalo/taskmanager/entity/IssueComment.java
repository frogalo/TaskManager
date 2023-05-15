package com.frogalo.taskmanager.entity;

import java.util.Date;

public class IssueComment extends Comment {
    private String issueSeverity;
    private String issueType;

    public IssueComment(String content, Date createdAt, User user, Task task, String issueCommentId, String issueSeverity, String issueType) {
        super(content, createdAt, user, task, issueCommentId, null);
        this.issueSeverity = issueSeverity;
        this.issueType = issueType;
    }

    public IssueComment() {
        super();
    }

    public String getIssueSeverity() {
        return issueSeverity;
    }

    public void setIssueSeverity(String issueSeverity) {
        this.issueSeverity = issueSeverity;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }
}
