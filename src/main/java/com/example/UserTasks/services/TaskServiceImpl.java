package com.example.UserTasks.services;

import com.example.UserTasks.data.TaskData;
import com.example.UserTasks.exceptions.DateNotValidException;
import com.example.UserTasks.exceptions.StatusNotValidException;
import com.example.UserTasks.model.Status;
import com.example.UserTasks.model.TaskModel;
import com.example.UserTasks.model.UserModel;
import com.example.UserTasks.populators.TaskPopulator;
import com.example.UserTasks.populators.TaskReversePopulator;
import com.example.UserTasks.repository.TaskDAO;
import com.example.UserTasks.repository.UserDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for task-related operations
 */
@Component
@Transactional
public class TaskServiceImpl {

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TaskPopulator taskPopulator;

    @Autowired
    private TaskReversePopulator taskReversePopulator;

    public void createTask(TaskData task) throws StatusNotValidException, ParseException, DateNotValidException {
        TaskModel newTask = new TaskModel();
        this.taskDAO.save(taskPopulator.populateTask(newTask, task));
    }

    public void editTask(TaskData task) throws StatusNotValidException, ParseException, DateNotValidException {
        TaskModel existingTask = this.taskDAO.getTaskByTitle(task.getTitle());
        this.taskDAO.save(taskPopulator.populateTask(existingTask, task));
    }

    public void deleteTaskByTitle(String title) {
        this.taskDAO.deleteByTitle(title);
    }

    public void changeTaskStatus(String title, String status) throws StatusNotValidException {
        TaskModel existingTask = this.taskDAO.getTaskByTitle(title);
        existingTask.setStatus(Status.parseStatus(status));
        this.taskDAO.save(existingTask);
    }

    public void assignUsersToTask(String title, List<String> usernames) {
        TaskModel existingTask = this.taskDAO.getTaskByTitle(title);
        List<UserModel> taskUsers = new ArrayList<>();
        for(String username : usernames) {
            UserModel existingUser = this.userDAO.getByName(username);
            List<TaskModel> userTasks = existingUser.getAssignedTasks();
            userTasks.add(existingTask);
            existingTask.setAssignedUsers(taskUsers);
            taskUsers.add(existingUser);
            this.userDAO.save(existingUser);
        }
        this.taskDAO.save(existingTask);
    }

    public List<TaskData> getTasksInGivenStatus(String taskStatus) throws StatusNotValidException {
        return this.taskReversePopulator.reversePopulateTaskList(this.taskDAO.getTasksByStatus(Status.parseStatus(taskStatus)));
    }
}
