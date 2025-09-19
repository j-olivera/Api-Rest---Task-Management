package com.taskManagement.domain.controllers;

import com.taskManagement.domain.entity.Task;
import com.taskManagement.domain.enums.Priority;
import com.taskManagement.domain.enums.ProjectStatus;
import com.taskManagement.domain.enums.TaskStatus;
import com.taskManagement.domain.mapper.TaskMapper;
import com.taskManagement.domain.request.TaskRequest;
import com.taskManagement.domain.response.ProjectResponse;
import com.taskManagement.domain.response.TaskResponse;
import com.taskManagement.domain.services.ProjectService;
import com.taskManagement.domain.services.TaskService;
import com.taskManagement.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/task")
public class TaskController {
    @Autowired
    TaskService taskService;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    ProjectService projectService;
    @Autowired
    UserService userService;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    //POST
    @PostMapping("/create")
    public ResponseEntity<TaskResponse> createTask(TaskRequest taskRequest){
        Task task = taskMapper.toEntity(taskRequest);//se crea entidad
        Task createdTask = taskService.createTask(task);//se guarda en la bd
        TaskResponse taskResponse = taskMapper.toResponse(createdTask); //lo mapeamos
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }

    //GET
    @GetMapping("/id/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("id") Long id){
        Task task = taskService.getTaskById(id);
        TaskResponse taskResponse = taskMapper.toResponse(task);
        return ResponseEntity.ok(taskResponse);
    }
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponse>> getAllTasks(){
        List<Task> tasks = taskService.getAllTasks();
        List<TaskResponse> taskResponses = tasks.stream()
                .map(taskMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskResponses);
    }
    @GetMapping("/projectID/{id}")
    public ResponseEntity<List<TaskResponse>> getAllProjectsById(@PathVariable("id") Long id){
        List<Task> tasks = taskService.getTaskByProjectId(id);
        List<TaskResponse> taskResponses = tasks
                .stream()
                .map(taskMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskResponses);
    }
    @GetMapping("/assigneID/{id}")
    public ResponseEntity<List<TaskResponse>> getAllAssigneesById(@PathVariable("id") Long id){
        List<Task> tasks = taskService.getTaskByAssigneeId(id);
        List<TaskResponse> taskResponses = tasks.stream()
                .map(taskMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskResponses);
    }
    @GetMapping("/taskstatus/{status}")
    public ResponseEntity<List<TaskResponse>> getAllTasksByStatus(@PathVariable("status") TaskStatus status){
       List<Task> tasks = taskService.getTaskByStatus(status);
       List<TaskResponse> taskResponses = tasks
               .stream()
               .map(taskMapper::toResponse)
               .collect(Collectors.toList());
        return ResponseEntity.ok(taskResponses);
    }
    @GetMapping("/taskpriority/{priority}")
    public ResponseEntity<List<TaskResponse>> getAllTasksByPriority(@PathVariable("priority") Priority priority){
        List<Task> tasks = taskService.getTaskByPriority(priority);
        List<TaskResponse> taskResponses = tasks.stream()
                .map(taskMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskResponses);
    }
    @GetMapping("/tasktitle/{title}")
    public ResponseEntity<List<TaskResponse>> getAllTasksByTitle(@PathVariable("title") String title){
        List<Task> tasks = taskService.getTaskByTitle(title);
        List<TaskResponse> taskResponses = tasks.stream()
                .map(taskMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskResponses);
    }
    @GetMapping("/duetask/{due}")
    public ResponseEntity<List<TaskResponse>> getAllTasksByDue(@PathVariable("due") LocalDate due){
        List<Task> tasks = taskService.getTaskDue(due);
        List<TaskResponse> taskResponses = tasks.stream()
                .map(taskMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskResponses);
    }
    //PUT
    @PutMapping("/id/{id}")
    public ResponseEntity<TaskResponse> updateTaskFromRequest(@PathVariable("id") Long taskId, @RequestBody TaskRequest taskRequest){
        Task updatedTask = taskService.updateTask(taskId,taskRequest);
        TaskResponse taskResponse = taskMapper.toResponse(updatedTask);
        return ResponseEntity.ok(taskResponse);
    }
    @PutMapping("/project/{id}")
    public ResponseEntity<TaskResponse> createTaskOnProject(@PathVariable("id") Long id, @RequestBody Task task){
        Task taskOnProject = taskService.createTaskOnProject(task, id);
        TaskResponse taskResponse = taskMapper.toResponse(taskOnProject);
        return ResponseEntity.ok(taskResponse);

    }
    @PutMapping("/tasks/{taskid}/assign/{userid}")
    public ResponseEntity<TaskResponse> assignTaskToUser(@PathVariable("taskid") Long taskid, @PathVariable("userid") Long userid){
        Task task = taskService.assignTask(taskid, userid);
        TaskResponse taskResponse = taskMapper.toResponse(task);
        return ResponseEntity.ok(taskResponse);
    }

    @PutMapping("/task/{taskid}/priotity")
    public ResponseEntity<TaskResponse> updatePriority(@PathVariable("taskid") Long taskid, @RequestBody Priority priority){
        Task task = taskService.changePriority(taskid, priority);
        TaskResponse taskResponse = taskMapper.toResponse(task);
        return ResponseEntity.ok(taskResponse);
    }
    @PutMapping("/task/{taskid}/status")
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable("taskid") Long taskid, @RequestBody TaskStatus status){
        Task task = taskService.changeStatus(taskid, status);
        TaskResponse taskResponse = taskMapper.toResponse(task);
        return ResponseEntity.ok(taskResponse);
    }
    //DELETE
    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id){
        boolean ok = taskService.deletedTask(id);
        if (ok){
            return ResponseEntity.ok("Task with id: "+id+" deleted successfully");
        }else{
            throw new IllegalArgumentException("Bad Request");
        }
    }
    @DeleteMapping("/remove/project/{taskid}")
    public ResponseEntity<String> removeTaskFromProject(@PathVariable("taskid") Long taskid){
        Task task = taskService.removeTaskFromProject(taskid);
        if(task.getProject()==null){
            return ResponseEntity.ok("Task with id: "+taskid+" removed successfully");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/remove/assign/{taskid}")
    public ResponseEntity<String> removeTaskFromAssign(@PathVariable("taskid") Long taskid){
        Task task = taskService.removeUserAssigneFromProject(taskid);
        return ResponseEntity.ok("Task with id: "+taskid+" removed successfully");
    }
}
