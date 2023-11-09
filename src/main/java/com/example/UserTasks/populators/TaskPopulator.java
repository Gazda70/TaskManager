package com.example.UserTasks.populators;

import com.example.UserTasks.data.TaskData;
import com.example.UserTasks.exceptions.DateNotValidException;
import com.example.UserTasks.exceptions.StatusNotValidException;
import com.example.UserTasks.model.Status;
import com.example.UserTasks.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * Class for populating task models
 */
@Component
public class TaskPopulator {

    @Autowired
    private PopulationUtils populationUtils;

    public TaskModel populateTask(TaskModel taskModel, TaskData taskData) throws StatusNotValidException, DateNotValidException, ParseException {
        taskModel.setTitle(taskData.getTitle());
        taskModel.setStatus(Status.parseStatus(taskData.getStatus()));
        taskModel.setDescription(taskData.getDescription());
        taskModel.setDueDate(populationUtils.parseDate(taskData.getDueDate()));
        return taskModel;
    }
}
