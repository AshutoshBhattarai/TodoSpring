package com.application.todo.Configs;

import com.application.todo.Models.APIResponse;
import com.application.todo.Services.ResponseCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Configuration
public class ExtraConfig {

    @Bean
    public ResponseCreator responseCreator() {
        return new ResponseCreator();
    }
}
