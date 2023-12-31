package com.example.UserTasks.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Data object for user related information
 */
@Data
public class UserData {
    @JsonProperty
    private String name;
    @JsonProperty
    private String surname;
    @JsonProperty
    private String email;
}
