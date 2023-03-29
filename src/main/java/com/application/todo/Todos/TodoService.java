package com.application.todo.Todos;

import com.application.todo.Users.UserModel;
import com.application.todo.Users.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepo todoRepo;
    private final UserRepo userRepo;

    public List<TodoModel> getTodos() {
        return todoRepo.findAll();
    }

    public TodoModel saveTodo(TodoModel todo) throws RuntimeException {
        UserModel user = userRepo
                .findById(todo.getUser().getId())
                .orElseThrow(()-> new RuntimeException("User not found"));
        TodoModel saveTodo = TodoModel.builder()
                .title(todo.getTitle())
                .completed(false)
                .completeOn(todo.getCompleteOn())
                .description(todo.getDescription())
                .user(user)
                .build();
        return todoRepo.save(saveTodo);
    }

    public List<Map<String,Object>> getTodosById(int id)
    {
        return todoRepo.getTodos(id);
    }
}
