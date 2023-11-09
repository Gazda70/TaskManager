package com.example.UserTasks.data;

import lombok.*;

/**
 * Data object for user related information
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserData {
    private String name;
    private String surname;
    private String email;
}
