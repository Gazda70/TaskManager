package com.example.UserTasks.data;

import lombok.*;

/**
 * Data object for task related information
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskData {
    private String title;
    private String description;
    private String status;
    private String dueDate;
}
