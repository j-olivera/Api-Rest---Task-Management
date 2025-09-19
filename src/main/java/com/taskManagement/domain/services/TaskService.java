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

import java.time.LocalDate;
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

    public List<Task> getTaskByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }
    public List<Task> getTaskByTitle(String title) {
        return taskRepository.findByTitle(title);
    }
    public List<Task> getTaskDue(LocalDate date){
        return taskRepository.findByDueDate(date);
    }

    //PUT
    /*
      PUT Crear tarea en un proyecto
      PUT Asignar tarea a un usuario
      PUT Cambiar status de tarea
      PUT Cambiar prioridad de tarea
      GET Obtener tareas por proyecto/usuario/status
      GET Buscar tareas por título
      GET Obtener tareas vencidas
     */

    public Task createTaskOnProject(Task task, Long projectId){
        if(task==null){throw new IllegalArgumentException("Task cannot be null");}
        Project project = projectRepository.findById(projectId).orElseThrow(()-> new ProjectNotFoundException("Project with id: "+projectId+" not found"));
        task.setProject(project);
        //horarios
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        //si no tiene estado, establecerlo
        if(task.getStatus()==null){
            task.setStatus(TaskStatus.TODO);
        }

        return taskRepository.save(task);
    }

    public Task assignTask(Long taskid, Long userId) {
        User user_assign = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User with id: "+userId+" not found.\nNot possible assigned the task"));
        Task task = taskRepository.findById(taskid).orElseThrow(()-> new TaskNotFoundException("Task with id: "+taskid+" not found"));
        task.setAssignee(user_assign);
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public Task changeStatus(Long taskId, TaskStatus status) {
        if(status==null){
            throw new IllegalArgumentException("TaskStatus cannot be null");
        }
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Task with id: "+taskId+" not found"));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    public Task changePriority(Long taskId, Priority priority) {
        if(priority==null){
            throw new IllegalArgumentException("TaskPriority cannot be null");
        }
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Task with id: "+taskId+" not found"));
        task.setPriority(priority);
        return taskRepository.save(task);
    }

    //delete
    //eliminar tarea
    public void deletedRask(Long taskId/*Long projectId*/) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Task with id: "+taskId+" not found"));
        //Project project = projectRepository.findById(projectId).orElseThrow(()-> new ProjectNotFoundException("Project with id: "+projectId+" not found"));
        taskRepository.delete(task);
    }
    //eliminar tarea desde un proyecto
    public Task removeTaskFromProject(Long taskId){
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Task with id: "+taskId+" not found"));
        task.setProject(null);
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }
    //eliminar usuario asignado a tal tarea
    //...
}
