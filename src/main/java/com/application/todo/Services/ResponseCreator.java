package com.application.todo.Services;

import com.application.todo.Models.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ResponseCreator {

    public ResponseEntity<?> successMessage(Object response) {
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("message", response);
        APIResponse apiResponse = APIResponse.builder()
                .status(HttpStatus.OK)
                .data(message)
                .statusCode(200)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    public ResponseEntity<?> successMessage(Map<String, Object> data) {
        APIResponse apiResponse = APIResponse.builder()
                .status(HttpStatus.OK)
                .data(data)
                .statusCode(200)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    public ResponseEntity<?> errorMessage(Object response) {
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("message", response);
        APIResponse apiResponse = APIResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .data(message)
                .statusCode(400)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
