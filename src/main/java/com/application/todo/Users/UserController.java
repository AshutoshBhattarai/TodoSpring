package com.application.todo.Users;

import com.application.todo.Services.ResponseCreator;
import com.application.todo.Users.RequestHandlers.UserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ResponseCreator responseCreator;

    @GetMapping
    private String getHome() {
        return userService.getHomePage();
    }

    @PostMapping("/save")
    private ResponseEntity<?> saveUser(@RequestBody UserModel user) {
        try {
            UserModel savedUser = userService.saveUser(user);
            return responseCreator.successMessage(savedUser);
        } catch (Exception e) {
            return responseCreator.errorMessage(e.getMessage());
        }
    }
}
