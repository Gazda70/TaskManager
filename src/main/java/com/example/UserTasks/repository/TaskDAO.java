package com.example.UserTasks.repository;

import com.example.UserTasks.model.Status;
import com.example.UserTasks.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Data Access Object for accessing task objects
 */
public interface TaskDAO extends JpaRepository<TaskModel, Long> {
    List<TaskModel> getTasksByStatus(final Status status);
    TaskModel getTaskByTitle(final String title);
    void deleteByTitle(final String title);
    List<TaskModel> findAllByAssignedUsers_Name(String name);
}
