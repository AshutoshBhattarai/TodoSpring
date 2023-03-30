package com.application.todo.Authentication;

import com.application.todo.JWT.JwtService;
import com.application.todo.Users.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final JwtService jwtService;


    public String getAuthToken(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(), request.getPassword()
        ));
        var user = userRepo.findUserByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User with email " + request.getEmail() + " not found"));
        Map<String, Object> claims = new HashMap<>();
        claims.put("Id",user.getId());
        return jwtService.generateToken(claims,user);

    }

}
