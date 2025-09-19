package com.taskManagement.domain.controllers;

import com.taskManagement.domain.entity.Project;
import com.taskManagement.domain.entity.User;
import com.taskManagement.domain.enums.ProjectStatus;
import com.taskManagement.domain.mapper.ProjectMapper;
import com.taskManagement.domain.mapper.UserMapper;
import com.taskManagement.domain.request.ProjectRequest;
import com.taskManagement.domain.request.UserRequest;
import com.taskManagement.domain.response.ProjectResponse;
import com.taskManagement.domain.services.ProjectService;
import com.taskManagement.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    public ProjectController(ProjectService projectService,
                             ProjectMapper projectMapper,
                             UserService userService,
                             UserMapper userMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
        this.userService = userService;
        this.userMapper = userMapper;
    }
    //GET
    @GetMapping("/projects")
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        List<ProjectResponse> projectResponses = projects.stream()
                .map(projectMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectResponses);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        Project project = projectService.findById(id);
        ProjectResponse projectResponse = projectMapper.toResponse(project);
        return ResponseEntity.ok(projectResponse);
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProjectResponse>> getAllProjectsByStatus(@PathVariable("status") ProjectStatus status) {
        List<Project> projects = projectService.findByProjectStatus(status);
        List<ProjectResponse> projectResponses = projects.stream()
                .map(projectMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectResponses);
    }
    @GetMapping("/user/{user}")
    public ResponseEntity<List<ProjectResponse>> getAllProjectsByUser(@PathVariable("user") Long id) {
        User user = userService.getUserById(id);
        String username = user.getUsername();
        List<Project> projects = projectService.findProjectsByUser(username);
        List<ProjectResponse> projectResponses = projects.stream()
                .map(projectMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectResponses);
    }
    //POST
    @PostMapping("/create")
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest projectRequest){
        Project project = projectMapper.toEntity(projectRequest);
        Project createdProject = projectService.createProject(project);
        ProjectResponse projectResponse = projectMapper.toResponse(createdProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectResponse);
    }
    //PUT
    @PutMapping("/id/{id}/update")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable("id") Long id, @RequestBody ProjectRequest projectRequest){
        Project updatedProject = projectService.updateProject(id, projectRequest);
        ProjectResponse projectResponse = projectMapper.toResponse(updatedProject);
        return ResponseEntity.ok(projectResponse);
    }
    @PutMapping("/id/{id}/add-member")
    public ResponseEntity<ProjectResponse> addMemberToProject(@PathVariable("id") Long id, @RequestBody UserRequest userRequest){
        User user = userMapper.toEntity(userRequest);
        Project added = projectService.addMember(user, id);
        ProjectResponse projectResponse = projectMapper.toResponse(added);
        return ResponseEntity.ok(projectResponse);
    }
    @PutMapping("/id/{id}/change-status")
    public ResponseEntity<ProjectResponse> changeProjectStatus(@PathVariable("id") Long id, @RequestBody ProjectStatus projectStatus){
        Project project = projectService.changeStatus(id, projectStatus);
        ProjectResponse projectResponse = projectMapper.toResponse(project);
        return ResponseEntity.ok(projectResponse);
    }
    //DELETE
    @DeleteMapping("/userId/{id}")
    public ResponseEntity<String> deleteMemberFromProject(@PathVariable("id") Long id, @RequestBody Long userId){
        Project project = projectService.deleteMember(id,userId);
        return ResponseEntity.ok("User eliminated from the project");
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable("id") Long id){
        boolean ok = projectService.deleteProject(id);
        if(ok){
            return ResponseEntity.ok("Project deleted from the DataBase");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project Not Found");
        }
    }
}

