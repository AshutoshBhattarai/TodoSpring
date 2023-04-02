package com.application.todo.Authentication;

import com.application.todo.Services.ResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    ResponseCreator responseCreator;
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("message", "User logged in successfully");
            data.put("token", "Bearer " +authService.getAuthToken(request));
            return responseCreator.successMessage(data);
        } catch (Exception e) {
            return responseCreator.errorMessage(e.getMessage());
        }

    }
}
