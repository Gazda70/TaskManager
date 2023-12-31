package com.example.UserTasks.controller;

import com.example.UserTasks.data.TaskData;
import com.example.UserTasks.data.TaskDataList;
import com.example.UserTasks.data.UsersToTaskData;
import com.example.UserTasks.exceptions.*;
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
     *
     * @param username task status
     * @return task list
     */
    @GetMapping("/getTasksForGivenUser")
    public List<TaskData> getTasksForGivenUser(@RequestParam String username) {
        try {
            return taskService.getTasksForGivenUsername(username);
        } catch (StatusNotValidException statusNotValidException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task status");
        } catch (DateNotValidException dateNotValidException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task date");
        }
    }

    /**
     * Get task having a given status.
     *
     * @param taskStatus task status
     * @return task list
     */
    @GetMapping(value = "/getTasksInGivenStatus")
    public TaskDataList getTasksInGivenStatus(@RequestParam String taskStatus) {
        try {
            return new TaskDataList(taskService.getTasksInGivenStatus(taskStatus));
        } catch (StatusNotValidException statusNotValidException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task status");
        } catch (DateNotValidException dateNotValidException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task date");
        }
    }

    /**
     * Create a task
     *
     * @param task Task body
     */
    @PostMapping("/create")
    public void createTask(@RequestBody TaskData task) {
        try {
            taskService.createTask(task);
        } catch (StatusNotValidException statusNotValidException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task status");
        } catch (DateNotValidException | ParseException dateNotValidException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task date");
        } catch (IncompleteInputDataException incompleteInputDataException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incomplete task data");
        } catch (ObjectAlreadyExistsException objectAlreadyExistsException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task already exists");
        }
    }

    /**
     * Edit task.
     *
     * @param task Task body
     */
    @PostMapping("/edit")
    public void editTask(@RequestBody TaskData task) {
        try {
            taskService.editTask(task);
        } catch (StatusNotValidException statusNotValidException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task status");
        } catch (DateNotValidException | ParseException dateNotValidException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task date");
        } catch (IncompleteInputDataException incompleteInputDataException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incomplete task data");
        } catch (ObjectDoesNotExistException objectDoesNotExistException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task does not exist");
        }
    }

    /**
     * Delete task
     *
     * @param title task title
     */
    @GetMapping("/delete")
    public void deleteTask(@RequestParam String title) {
        taskService.deleteTaskByTitle(title);
    }

    /**
     * Change task status
     *
     * @param title  task title
     * @param status task status
     */
    @GetMapping("/changeTaskStatus")
    public void changeTaskStatus(@RequestParam String title, @RequestParam String status) {
        try {
            taskService.changeTaskStatus(title, status);
        } catch (StatusNotValidException statusNotValidException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task status");
        }
    }

    /**
     * Assign users to task
     *
     * @param usersToTaskData connection between users and task
     */
    @PostMapping("/assignUsersToTask")
    public void assignUsersToTask(@RequestBody UsersToTaskData usersToTaskData) {
        taskService.assignUsersToTask(usersToTaskData.getTitle(), usersToTaskData.getUsernames());
    }
}
