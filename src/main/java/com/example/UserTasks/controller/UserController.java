package com.example.UserTasks.controller;

import com.example.UserTasks.data.UserData;
import com.example.UserTasks.data.UserDataList;
import com.example.UserTasks.exceptions.IncompleteInputDataException;
import com.example.UserTasks.exceptions.ObjectAlreadyExistsException;
import com.example.UserTasks.exceptions.StatusNotValidException;
import com.example.UserTasks.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("app/users")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * Create user
     *
     * @param user userBody
     */
    @PostMapping("/create")
    public void createUser(@RequestBody UserData user) {
        try {
            userService.createUser(user);
        } catch (IncompleteInputDataException incompleteInputDataException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incomplete user data");
        } catch (ObjectAlreadyExistsException objectAlreadyExistsException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
    }

    /**
     * Delete user
     *
     * @param name name of user to delete
     */
    @GetMapping("/delete")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUserByName(name);
    }

    /**
     * Get all users that have at least one task in given status
     *
     * @param taskStatus task status
     */
    @GetMapping("/getUsersWithTasksInStatus")
    public UserDataList getUsersWithTasksInStatus(@RequestParam String taskStatus) {
        try {
            return new UserDataList(userService.getUsersWithTaskStatus(taskStatus));
        } catch (StatusNotValidException statusNotValidException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task status");
        }
    }

    /**
     * Get users with given task assigned
     *
     * @param taskTitle task title
     */
    @GetMapping("/getUsersWithTask")
    public UserDataList getUsersWithTask(@RequestParam String taskTitle) {
        return new UserDataList(userService.getUsersWithTask(taskTitle));
    }
}
