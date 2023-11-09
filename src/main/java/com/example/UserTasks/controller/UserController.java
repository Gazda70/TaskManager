package com.example.UserTasks.controller;

import com.example.UserTasks.exceptions.StatusNotValidException;
import com.example.UserTasks.model.UserModel;
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

    @PostMapping("/create")
    public void createUser(UserModel user) {
        userService.createUser(user);
    }

    @GetMapping("/delete")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUserByName(name);
    }

    @GetMapping("/getUsersWithTasksInStatus")
    public void getUsersWithTasksInStatus(@RequestParam String taskStatus) {
        try{
            userService.getUsersWithTaskStatus(taskStatus);
        } catch(
        StatusNotValidException statusNotValidException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task status");
        }
    }

    @GetMapping("/getUsersWithTask")
    public void getUsersWithTask(@RequestParam String taskTitle) {
        userService.getUsersWithTask(taskTitle);
    }
}
