package com.example.UserTasks.data;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersToTaskData {
    private String title;
    private List<String> usernames;
}
