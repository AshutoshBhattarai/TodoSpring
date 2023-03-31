package com.application.todo.Todos.RequestHandlers;

import com.application.todo.Users.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoSaveReq {
    String title;
    String description;
    LocalDate completeOn;
    Boolean completed;
    private UserModel user;
}
