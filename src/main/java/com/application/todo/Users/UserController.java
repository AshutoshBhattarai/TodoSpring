package com.application.todo.Users;

import com.application.todo.JWT.JwtService;
import com.application.todo.Services.ResponseCreator;
import com.application.todo.Users.RequestHandlers.UserRes;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ResponseCreator responseCreator;
    private final JwtService jwtService;

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
    @GetMapping("/profile")
    private ResponseEntity<?> getUser(HttpServletRequest request)
    {
        try {
            int id = getIdFromToken(request);
            var user = userService.getUserById(id);
            return responseCreator.successMessage(user);
        } catch (Exception e) {
            return responseCreator.errorMessage(e.getMessage());
        }
    }
    private int getIdFromToken(HttpServletRequest request) throws Exception {
        String getToken = request.getHeader("Authorization").substring(7);
        Claims claims = jwtService.getAllClaims(getToken);
        Object user_id = claims.get("Id");
        return Integer.parseInt(user_id.toString());
    }
}
