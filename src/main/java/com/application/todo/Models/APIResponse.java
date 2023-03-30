package com.application.todo.Models;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class APIResponse {

    HttpStatus status;
    Map<String, Object> data;
    int statusCode;
    LocalDateTime timestamp;


}
