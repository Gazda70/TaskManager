package com.example.UserTasks.repository;

import com.example.UserTasks.model.Status;
import com.example.UserTasks.model.TaskModel;
import com.example.UserTasks.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Data Access Object for accessing task objects
 */
public interface TaskDAO extends JpaRepository<TaskModel, Long> {
    List<TaskModel> getTasksByStatus(final Status status);
    TaskModel getTaskByTitle(final String title);
    void deleteByTitle(final String title);
    @Query("SELECT t FROM TaskModel t JOIN UserModel u WHERE u.name  = :userName")
    List<UserModel> findAllByUserName(@Param("userName")String userName);
}
