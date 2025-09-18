package com.taskManagement.domain.repository;

import com.taskManagement.domain.entity.Project;
import com.taskManagement.domain.enums.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    List<Project> findProjectsByOwnerUsername(String ownerUsername);
    List<Project> findProjectsByStatus(ProjectStatus projectStatus);
    //List<Task> findStadisticTasksByProjectStatus(ProjectStatus projectStatus );
}
