package com.application.todo.Configs;

import com.application.todo.Services.ResponseCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExtraConfig {
    @Bean
    public ResponseCreator responseCreator() {
        return new ResponseCreator();
    }
}
