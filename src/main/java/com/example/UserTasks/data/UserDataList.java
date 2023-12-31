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
@JacksonXmlRootElement(localName = "user-data-list")
public class UserDataList implements Serializable {
    @JacksonXmlElementWrapper(localName = "user-list", useWrapping = true)
    @JsonProperty("user")
    private List<UserData> users;
}
