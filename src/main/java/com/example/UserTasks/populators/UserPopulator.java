package com.example.UserTasks.populators;

import com.example.UserTasks.data.UserData;
import com.example.UserTasks.exceptions.IncompleteInputDataException;
import com.example.UserTasks.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPopulator {

    @Autowired
    private Validator validator;

    public UserModel populateUser(UserModel userModel, UserData userData) throws IncompleteInputDataException {
        if(validator.isUserNotEmpty(userData)) {
            userModel.setName(userData.getName());
            userModel.setSurname(userData.getSurname());
            userModel.setEmail(userData.getEmail());
        } else {
            throw new IncompleteInputDataException();
        }
        return userModel;
    }

}
