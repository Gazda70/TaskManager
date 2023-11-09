package com.example.UserTasks.populators;

import com.example.UserTasks.data.TaskData;
import com.example.UserTasks.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Task for populating TaskData objects
 */
@Component
public class TaskReversePopulator {

    @Autowired
    private PopulationUtils populationUtils;

    public TaskData populateTask(TaskData taskData, TaskModel taskModel) {
        taskData.setTitle(taskModel.getTitle());
        taskData.setStatus(taskModel.getStatus().toString());
        taskData.setDescription(taskModel.getDescription());
        taskData.setDueDate(taskData.getDueDate());
        return taskData;
    }

    public List<TaskData> reversePopulateTaskList(List<TaskModel> taskModels) {
        List<TaskData> taskDataList = new ArrayList<>();
        for(TaskModel taskModel : taskModels) {
            TaskData taskData = new TaskData();
            taskDataList.add(this.populateTask(taskData, taskModel));
        }
        return taskDataList;
    }
}
