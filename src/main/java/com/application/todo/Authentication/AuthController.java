package com.application.todo.Authentication;

import com.application.todo.Services.ResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
              String token = authService.getAuthToken(request);
              return responseCreator.loginSuccess(token);
        } catch (Exception e) {
            return responseCreator.errorMessage(e.getMessage());
        }

    }
}
