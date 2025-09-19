package com.taskManagement.domain.mapper;

import com.taskManagement.domain.entity.Task;
import com.taskManagement.domain.request.TaskRequest;
import com.taskManagement.domain.response.TaskResponse;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    //entity -> dto
    public TaskResponse toResponse(Task task) {
        if(task == null){
            return null;
        }
        TaskResponse response = new TaskResponse();
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setPriority(task.getPriority());
        response.setProject(task.getProject());
        response.setAssignee(task.getAssignee());
        response.setReporter(task.getReporter());
        response.setEstimatedHours(task.getEstimatedHours());
        return response;
    }

    //request -> entity

    public Task toEntity(TaskRequest taskRequest) {
        if(taskRequest == null){
            return null;
        }
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setPriority(taskRequest.getPriority());
        task.setProject(taskRequest.getProject());
        task.setAssignee(taskRequest.getAssignee());
        task.setReporter(taskRequest.getReporter());
        task.setEstimatedHours(taskRequest.getEstimatedHours());
        task.setStatus(taskRequest.getStatus());
        task.setCreatedAt(taskRequest.getCreatedAt());
        task.setUpdatedAt(taskRequest.getUpdatedAt());
        task.setDueDate(taskRequest.getDueDate());
        return task;
    }

    //para actualizaciones -> bajo Request -> actualizo en entity
    public void updateDateFromRequest(TaskRequest taskRequest, Task existingTask) {
        if(taskRequest == null ||  existingTask == null){
            return;
        }
        //solo lo que se quiere actualizar
        existingTask.setTitle(taskRequest.getTitle());
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setPriority(taskRequest.getPriority());
        existingTask.setStatus(taskRequest.getStatus());

    }

}

