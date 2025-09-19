package com.taskManagement.domain.services;

import com.taskManagement.domain.entity.Project;
import com.taskManagement.domain.entity.Task;
import com.taskManagement.domain.entity.User;
import com.taskManagement.domain.enums.Priority;
import com.taskManagement.domain.enums.TaskStatus;
import com.taskManagement.domain.exceptions.ProjectNotFoundException;
import com.taskManagement.domain.exceptions.TaskNotFoundException;
import com.taskManagement.domain.exceptions.UserNotFoundException;
import com.taskManagement.domain.mapper.TaskMapper;
import com.taskManagement.domain.repository.ProjectRepository;
import com.taskManagement.domain.repository.TaskRepository;
import com.taskManagement.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    final
    TaskRepository taskRepository;
    final
    TaskMapper taskMapper;
    ProjectRepository projectRepository;
    UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, ProjectRepository projectRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    //POST
    public Task createTask(Task task) {
        if(task.getCreatedAt() == null){
            task.setCreatedAt(LocalDateTime.now());
        }
        return taskRepository.save(task);
    }

    /*

       List<Task> findByProject_Id(Long id);
        List<Task> findByUser_Id(Long id);
        List<Task> findByStatus(String status);
        List<Task> findByTitle(String title); */

    //GET
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException("Task with id: "+id+" not found"));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTaskByProjectId(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(()-> new ProjectNotFoundException("Project with id: "+projectId+" not found"));
        return taskRepository.findByProject_Id(project.getId());
    }

    public List<Task> getTaskByAssigneeId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User with id: "+userId+" not found"));
        return taskRepository.findByAssignee_Id(user.getId());
    }

    public List<Task> getTaskByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }
}
