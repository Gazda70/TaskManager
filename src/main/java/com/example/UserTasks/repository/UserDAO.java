package com.example.UserTasks.repository;

import com.example.UserTasks.model.Status;
import com.example.UserTasks.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Data Access Object for accessing user objects
 */
public interface UserDAO extends JpaRepository<UserModel, Long> {
    void deleteByName(String name);

    List<UserModel> findAllByAssignedTasks_Title(@Param("title")String title);

    List<UserModel> findAllByAssignedTasks_Status(@Param("status") Status status);

    UserModel getUserByName(String name);
}
