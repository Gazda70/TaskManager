package com.example.UserTasks.services;

import com.example.UserTasks.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    void createUser(final UserModel user);
    void deleteUserByName(final String userName);
}
