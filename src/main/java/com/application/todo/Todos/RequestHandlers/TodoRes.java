package com.application.todo.Todos.RequestHandlers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoRes {

    int id;
    String title;
    String description;
    LocalDate complete_on;
    Boolean completed;
}
