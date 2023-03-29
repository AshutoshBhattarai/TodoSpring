package com.application.todo.Users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    private String getHome()
    {
        return userService.getHomePage();
    }

    @PostMapping("/save")
    private ResponseEntity<?> saveUser(@RequestBody UserModel user)
    {
        UserModel savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }



}
