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


/**
 * Service for task-related operations
 */
@Component
@Transactional
public class UserServiceImpl {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserPopulator userPopulator;

    @Autowired
    private TaskDAO taskDAO;

    public void createUser(final UserData userData) throws IncompleteInputDataException, ObjectAlreadyExistsException {
        UserModel userModel = new UserModel();
        if(this.userDAO.getUserByName(userData.getName()) != null) {
            throw new ObjectAlreadyExistsException();
        }
        userDAO.save(userPopulator.populateUser(userModel, userData));
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
