package com.example.UserTasks.repository;

import com.example.UserTasks.model.Status;
import com.example.UserTasks.model.TaskModel;
import com.example.UserTasks.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDAO extends JpaRepository<UserModel, Long> {
    void deleteByName(String name);
    @Query("SELECT u FROM UserModel u JOIN TaskModel t WHERE t.title  = :taskTitle")
    List<UserModel> findAllByTaskTitle(@Param("taskTitle")String taskTitle);

    @Query("SELECT u FROM UserModel u JOIN TaskModel t WHERE t.status  = :taskStatus")
    List<UserModel> findAllByTaskStatus(@Param("taskStatus") Status taskStatus);

    UserModel getByName(String name);
}
