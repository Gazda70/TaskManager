package com.example.UserTasks.data;

import lombok.*;

import java.util.List;

/**
 * Data object for information about connecting users and task
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersToTaskData {
    private String title;
    private List<String> usernames;
}
