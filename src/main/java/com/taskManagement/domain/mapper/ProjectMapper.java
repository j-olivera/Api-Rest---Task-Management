package com.taskManagement.domain.mapper;

import com.taskManagement.domain.entity.Project;
import com.taskManagement.domain.request.ProjectRequest;
import com.taskManagement.domain.response.ProjectResponse;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    // -> copiar funciones desde userMapper, pasar a services luego controllers
    //entity -> dto
    public ProjectResponse toResponse(Project project){
        if(project == null){
            return null;
        }
        ProjectResponse response = new ProjectResponse();
        response.setOwner(project.getOwner());
        response.setProjectName(project.getName());
        response.setProjectDescription(project.getDescription());
        response.setStatus(project.getStatus());
        response.setMembers(project.getMembers());
        return response;
    }
    //Request a Entity
    public Project toEntity(ProjectRequest projectRequest){
        if(projectRequest==null){
            return null;
        }
        Project project = new Project();
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        project.setStatus(projectRequest.getStatus());
        project.setOwner(projectRequest.getOwner());
        project.setCreatedAt(projectRequest.getCreatedAt());
        project.setMembers(projectRequest.getMembers());
        return project;
    }
    //ActualizarEntidad_desde_Request
    public void updateDatoFromRequest(ProjectRequest projectRequest, Project existingProject){
        if(existingProject==null ||  projectRequest==null){
            return;
        }
        existingProject.setName(projectRequest.getName());
        existingProject.setDescription(projectRequest.getDescription());
        existingProject.setStatus(projectRequest.getStatus());
        //existingProject.setCreatedAt(projectRequest.getCreatedAt());
        existingProject.setMembers(projectRequest.getMembers());
        //existingProject.setOwner(projectRequest.getOwner());
    }

}
