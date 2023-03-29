package com.application.todo.Users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public String getHomePage() {
        return "Welcome to the User Home Page";
    }

    public UserModel saveUser(UserModel user) throws RuntimeException {
        if (userRepo.findUserByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        } else return userRepo.save(user);
    }
}
