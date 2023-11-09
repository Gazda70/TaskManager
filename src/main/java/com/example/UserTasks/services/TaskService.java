package com.example.UserTasks.services;

import com.example.UserTasks.model.TaskModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Service for task-related operations
 */
@Component
public interface TaskService {
    void saveTask(final TaskModel task);
    void deleteTaskByTitle(final String taskTitle);
    List<TaskModel> findByTitle(final String taskTitle);
}
