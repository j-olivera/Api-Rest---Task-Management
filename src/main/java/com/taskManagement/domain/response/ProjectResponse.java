package com.taskManagement.domain.response;

import com.taskManagement.domain.entity.User;
import com.taskManagement.domain.enums.ProjectStatus;

import java.util.List;

public class ProjectResponse {
    private String projectName;
    private String projectDescription;
    private User owner;
    private ProjectStatus status;
    private List<User> members;

    public ProjectResponse(String projectName, String projectDescription, User owner, ProjectStatus status, List<User> members) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.owner = owner;
        this.status = status;
        this.members = members;
    }

    public ProjectResponse() {
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
