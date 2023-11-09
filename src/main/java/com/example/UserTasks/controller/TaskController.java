package com.example.UserTasks.controller;

import com.example.UserTasks.data.TaskData;
import com.example.UserTasks.data.UsersToTaskData;
import com.example.UserTasks.exceptions.DateNotValidException;
import com.example.UserTasks.exceptions.StatusNotValidException;
import com.example.UserTasks.model.UserModel;
import com.example.UserTasks.services.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.List;

@RequestMapping("app/tasks")
@RestController
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @GetMapping("/getTasksInGivenStatus/{taskStatus}")
    public List<TaskData> getTasksInGivenStatus(String taskStatus) {
        try {
            return taskService.getTasksInGivenStatus(taskStatus);
        } catch(StatusNotValidException statusNotValidException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task status");
        }
    }

    @PostMapping("/create")
    public void createTask(TaskData task) {
        try {
            taskService.createTask(task);
        } catch (StatusNotValidException | ParseException | DateNotValidException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task data");
        }
    }

    @PostMapping("/edit")
    public void editTask(@Param("title") TaskData task) {
        try {
            taskService.editTask(task);
        } catch (StatusNotValidException | ParseException | DateNotValidException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task data");
        }
    }

    @GetMapping("/delete")
    public void deleteTask(@RequestParam String title) {
        taskService.deleteTaskByTitle(title);
    }

    @GetMapping("/changeTaskStatus")
    public void changeTaskStatus(@RequestParam String title, @RequestParam String status) {
        try {
            taskService.changeTaskStatus(title, status);
        } catch(StatusNotValidException statusNotValidException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task status");
        }
    }

    @PostMapping("/assignUsersToTask")
    public void assignUsersToTask(@RequestBody UsersToTaskData usersToTaskData) {
        taskService.assignUsersToTask(usersToTaskData.getTitle(), usersToTaskData.getUsernames());
    }
}
