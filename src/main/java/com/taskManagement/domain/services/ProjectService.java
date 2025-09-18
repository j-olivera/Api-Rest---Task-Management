package com.taskManagement.domain.services;

import com.taskManagement.domain.entity.Project;
import com.taskManagement.domain.entity.User;
import com.taskManagement.domain.enums.ProjectStatus;
import com.taskManagement.domain.exceptions.ProjectNotFoundException;
import com.taskManagement.domain.exceptions.UserNotFoundException;
import com.taskManagement.domain.mapper.ProjectMapper;
import com.taskManagement.domain.repository.ProjectRepository;
import com.taskManagement.domain.repository.UserRepository;
import com.taskManagement.domain.request.ProjectRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {
    final
    ProjectRepository projectRepository;
    final
    ProjectMapper projectMapper;
    UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepositoy, ProjectMapper projectMapper, UserRepository userRepository) {
        this.projectRepository = projectRepositoy;
        this.projectMapper = projectMapper;
        this.userRepository = userRepository;
    }

    //GET
    public Project findById(Long id){
        return projectRepository.findById(id).
                orElseThrow(() -> new ProjectNotFoundException("Project not found"));
    }

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }
    public List<Project> findByProjectStatus(ProjectStatus projectStatus){
        return projectRepository.findProjectsByStatus(projectStatus);
    }
    public List<Project> findProjectsByUser(String userName){
        return projectRepository.findProjectsByOwnerUsername(userName);
    }
    //POST
    public Project createProject(Project project){
        if(project.getCreatedAt()==null){
            project.setCreatedAt(LocalDateTime.now());
        }
        return projectRepository.save(project);
    }
    //PUT - CASOS DE ACTUALIZACIÓN
    public Project updateProject(Long id, ProjectRequest project){
        Project newProject = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        projectMapper.updateDatoFromRequest(project,newProject);
        return projectRepository.save(newProject);
    }
    public Project addMember(User user, Long id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        List<User> l_user = project.getMembers();
        l_user.add(user);
        project.setMembers(l_user);
        return projectRepository.save(project);
    }

    public Project changeStatus(Long id, ProjectStatus projectStatus){
        Project p = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        p.setStatus(projectStatus);
        return projectRepository.save(p);
    }

    //DELETE
    public boolean deleteProject(Long id){
        Project deleteP =  projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        projectRepository.deleteById(deleteP.getId());
        return true;
    }
    public Project deleteMember(Long id, Long userID){
        Project p =  projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        User user = userRepository.findById(userID).orElseThrow(()-> new UserNotFoundException("User not found"));
        List<User> l_user = p.getMembers();
        l_user.remove(user);
        return projectRepository.save(p);
    }

}
