package com.example.UserTasks.populators;

import com.example.UserTasks.data.TaskData;
import com.example.UserTasks.data.UserData;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    public boolean isTaskNotEmpty(final TaskData task) {
        return !task.getTitle().isEmpty() && !task.getDescription().isEmpty() && !task.getDueDate().isEmpty() && !task.getStatus().isEmpty();
    }
    public boolean isUserNotEmpty(final UserData user) {
        return !user.getName().isEmpty() && !user.getSurname().isEmpty() && !user.getEmail().isEmpty();
    }
}
