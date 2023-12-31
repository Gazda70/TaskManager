package com.example.UserTasks.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(localName = "task-data-list")
public class TaskDataList implements Serializable {
    @JacksonXmlElementWrapper(localName = "task-list", useWrapping = true)
    @JsonProperty("task")
    private List<TaskData> tasks;
}
