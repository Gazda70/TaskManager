package com.example.UserTasks.controller;

import com.example.UserTasks.data.TaskData;
import com.example.UserTasks.data.UsersToTaskData;
import com.example.UserTasks.exceptions.DateNotValidException;
import com.example.UserTasks.exceptions.StatusNotValidException;
import com.example.UserTasks.services.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Get task having a given status.
     * @param taskStatus task status
     * @return task list
     */
    @GetMapping("/getTasksInGivenStatus")
    public List<TaskData> getTasksInGivenStatus(@RequestParam String taskStatus) {
        try {
            return taskService.getTasksInGivenStatus(taskStatus);
        } catch(StatusNotValidException statusNotValidException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task status");
        }
    }

    /**
     * Create a task
     * @param task Task body
     */
    @PostMapping("/create")
    public void createTask(@RequestBody TaskData task) {
        try {
            taskService.createTask(task);
        } catch (StatusNotValidException | ParseException | DateNotValidException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task data");
        }
    }

    /**
     * Edit task.
     * @param task Task body
     */
    @PostMapping("/edit")
    public void editTask(@RequestBody TaskData task) {
        try {
            taskService.editTask(task);
        } catch (StatusNotValidException | ParseException | DateNotValidException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task data");
        }
    }

    /**
     * Delete task
     * @param title task title
     */
    @GetMapping("/delete")
    public void deleteTask(@RequestParam String title) {
        taskService.deleteTaskByTitle(title);
    }

    /**
     * Change task status
     * @param title task title
     * @param status task status
     */
    @GetMapping("/changeTaskStatus")
    public void changeTaskStatus(@RequestParam String title, @RequestParam String status) {
        try {
            taskService.changeTaskStatus(title, status);
        } catch(StatusNotValidException statusNotValidException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task status");
        }
    }

    /**
     * Assign users to task
     * @param usersToTaskData connection between users and task
     */
    @PostMapping("/assignUsersToTask")
    public void assignUsersToTask(@RequestBody UsersToTaskData usersToTaskData) {
        taskService.assignUsersToTask(usersToTaskData.getTitle(), usersToTaskData.getUsernames());
    }
}
