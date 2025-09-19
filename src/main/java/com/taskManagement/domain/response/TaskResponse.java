package com.taskManagement.domain.response;

import com.taskManagement.domain.entity.Project;
import com.taskManagement.domain.entity.User;
import com.taskManagement.domain.enums.Priority;
import com.taskManagement.domain.enums.TaskStatus;

public class TaskResponse {
    private String title;
    private String description;
    private Project project;
    private User assignee;
    private User reporter;
    private TaskStatus status;
    private Priority priority;
    private Integer estimatedHours;

    public TaskResponse() {}

    public TaskResponse(String title, String description, Project project, User assignee, User reporter, TaskStatus status, Priority priority, Integer estimatedHours) {
        this.title = title;
        this.description = description;
        this.project = project;
        this.assignee = assignee;
        this.reporter = reporter;
        this.status = status;
        this.priority = priority;
        this.estimatedHours = estimatedHours;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Integer getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Integer estimatedHours) {
        this.estimatedHours = estimatedHours;
    }
}
