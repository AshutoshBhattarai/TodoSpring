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
    public UserModel saveUser(UserModel user) {
        return userRepo.save(user);
    }

}
