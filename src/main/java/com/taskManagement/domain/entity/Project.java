package com.taskManagement.domain.entity;

import com.taskManagement.domain.enums.ProjectStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(length=100)
    private String name;
    @Column(length=100)
    private String description;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private User owner;
    @ManyToMany(cascade=CascadeType.ALL)
    private List<User> members;
    @Column()
    private LocalDateTime createdAt;
    @Column()
    private LocalDate deadline;
    @Column()
    private ProjectStatus status;

    public Project() {}

    public Project(Long id, String name, String description, User owner, List<User> members, LocalDateTime createdAt, LocalDate deadline, ProjectStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.members = members;
        this.createdAt = createdAt;
        this.deadline = deadline;
        this.status = status;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public User getOwner() {return owner;}
    public void setOwner(User owner) {this.owner = owner;}

    public List<User> getMembers() {return members;}
    public void setMembers(List<User> members) {this.members = members;}

    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    public LocalDate getDeadline() {return deadline;}
    public void setDeadline(LocalDate deadline) {this.deadline = deadline;}

    public ProjectStatus getStatus() {return status;}
    public void setStatus(ProjectStatus status) {this.status = status;}

}
