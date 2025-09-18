package com.taskManagement.domain.request;

import com.taskManagement.domain.entity.User;
import com.taskManagement.domain.enums.ProjectStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ProjectRequest {

    private Long id;
    private String name;
    private String description;
    private User owner;
    private List<User> members;
    private LocalDateTime createdAt;
    private LocalDate deadline;
    private ProjectStatus status;

    public ProjectRequest(){}

    public ProjectRequest(Long id, String name, String description, User owner, List<User> members, LocalDateTime createdAt, LocalDate deadline, ProjectStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.members = members;
        this.createdAt = createdAt;
        this.deadline = deadline;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}
