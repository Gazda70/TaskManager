package com.example.UserTasks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
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
    public UserModel(String Name, String Surname, String Email) {
        this.name = Name;
        this.surname = Surname;
        this.email = Email;
    }
}
