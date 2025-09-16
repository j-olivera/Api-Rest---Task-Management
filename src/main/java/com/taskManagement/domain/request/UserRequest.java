package com.taskManagement.domain.request;

import com.taskManagement.domain.entity.User;

import java.time.LocalDateTime;

public class UserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime createdAt;
    private Boolean active;

    public UserRequest(){}

    public UserRequest(String username, String firstName, String lastName, String email, LocalDateTime createdAt, Boolean active) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.createdAt = createdAt;
        this.active = active;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


