package com.example.UserTasks.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Class for modelling user information
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String email;
    @ManyToMany
    private List<TaskModel> assignedTasks;
}
