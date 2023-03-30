package com.application.todo.Users;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public String getHomePage() {
        return "Welcome to the User Home Page";
    }

    public UserModel saveUser(UserModel user) throws RuntimeException {

        UserModel userModel = UserModel.builder()
                .email(user.getEmail())
                .name(user.getName())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();
        if (userRepo.findUserByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        } else return userRepo.save(userModel);
    }
}
