package com.application.todo.Models;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class APIResponse {

    HttpStatus status;
    Object message;
    int statusCode;
    LocalDateTime timestamp;


}
