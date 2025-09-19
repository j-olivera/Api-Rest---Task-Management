package com.taskManagement.domain.repository;

import com.taskManagement.domain.entity.Task;
import com.taskManagement.domain.enums.Priority;
import com.taskManagement.domain.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
        List<Task> findByProject_Id(Long id);
        List<Task> findByAssignee_Id(Long id);
        List<Task> findByStatus(TaskStatus status);
        List<Task> findByPriority(Priority priority);
        List<Task> findByTitle(String title);
        List<Task> findByDueDate(LocalDate dueDate);
}
