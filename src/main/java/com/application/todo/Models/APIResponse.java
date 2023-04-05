package com.application.todo.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    Object message;
    int statusCode;
    LocalDateTime timestamp;


}
