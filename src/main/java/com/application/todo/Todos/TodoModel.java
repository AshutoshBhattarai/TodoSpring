package com.application.todo.Todos;

import com.application.todo.Users.UserModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Entity
@Table(name = "todos")
public class TodoModel {

    @Id
    @GeneratedValue
    int id;
    String title;
    String description;
    LocalDate completeOn;
    @Builder.Default
    Boolean completed = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserModel user;

}
