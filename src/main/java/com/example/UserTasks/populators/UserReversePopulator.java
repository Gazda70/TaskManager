package com.example.UserTasks.populators;

import com.example.UserTasks.data.UserData;
import com.example.UserTasks.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserReversePopulator {

    public UserData populateTask(UserData userData, UserModel userModel) {
        userData.setName(userModel.getName());
        userData.setSurname(userModel.getSurname());
        userData.setEmail(userModel.getEmail());
        return userData;
    }

    public List<UserData> reversePopulateUserList(List<UserModel> userModels) {
        List<UserData> taskDataList = new ArrayList<>();
        for(UserModel userModel : userModels) {
            UserData userData = new UserData();
            taskDataList.add(this.populateTask(userData, userModel));
        }
        return taskDataList;
    }
}
