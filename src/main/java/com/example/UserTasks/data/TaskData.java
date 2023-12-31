package com.example.UserTasks.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

/**
 * Data object for task related information
 */
@Data
public class TaskData implements Serializable {
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
    @JsonProperty("dueDate")
    private String dueDate;
}
