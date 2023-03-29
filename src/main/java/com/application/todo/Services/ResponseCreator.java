package com.application.todo.Services;

import com.application.todo.Models.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseCreator {

    public ResponseEntity<?> successMessage(Object response) {
        APIResponse apiResponse = APIResponse.builder()
                .status(HttpStatus.OK)
                .message(response)
                .statusCode(200)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    public ResponseEntity<?> errorMessage(Object response) {
        APIResponse apiResponse = APIResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(response)
                .statusCode(400)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.badRequest().body(apiResponse);
    }

}
