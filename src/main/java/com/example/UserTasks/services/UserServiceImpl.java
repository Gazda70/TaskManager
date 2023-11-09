package com.example.UserTasks.services;

import com.example.UserTasks.exceptions.StatusNotValidException;
import com.example.UserTasks.model.Status;
import com.example.UserTasks.model.TaskModel;
import com.example.UserTasks.model.UserModel;
import com.example.UserTasks.repository.TaskDAO;
import com.example.UserTasks.repository.UserDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public class UserServiceImpl {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TaskDAO taskDAO;

    public void createUser(final UserModel user) {
        userDAO.save(user);
    }

    public void deleteUserByName(final String userName) {
        userDAO.deleteByName(userName);
    }

    public List<UserModel> getUsersWithTaskStatus(String status) throws StatusNotValidException {
        return userDAO.findAllByTaskStatus(Status.parseStatus(status));
    }

    public List<UserModel> getUsersWithTask(String title) {
        return userDAO.findAllByTaskTitle(title);
    }

}
