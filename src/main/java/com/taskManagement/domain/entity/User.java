package com.taskManagement.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String username;
    @Column(unique=true)
    private String email;
    @Column()
    private String firstName;
    @Column()
    private String lastName;
    @Column()
    private LocalDateTime createdAt;
    @Column()
    private Boolean active;;

    public User(){}

    public User(String username, String email, String firstName, String lastName, Boolean active) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        this.createdAt = LocalDateTime.now();

    }

    public Long getId() {return id;}
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public Boolean getActive() {return active;}
    public void setActive(Boolean active) {this.active = active;}

    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    //
    @PrePersist
    public void prePersist(){
        if(this.createdAt == null){this.createdAt = LocalDateTime.now();}
    }
}
