package com.application.todo.Todos;

import com.application.todo.Todos.RequestHandlers.TodoRes;
import com.application.todo.Todos.RequestHandlers.TodoSaveReq;
import com.application.todo.Todos.RequestHandlers.TodoUpdateReq;
import com.application.todo.Users.UserModel;
import com.application.todo.Users.UserRepo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepo todoRepo;
    private final UserRepo userRepo;
    private final ObjectMapper mapper;

    public List<TodoModel> getTodos() {
        return todoRepo.findAll();
    }

    public String saveTodo(TodoSaveReq todo, int id) throws RuntimeException {
        UserModel user = userRepo
                .findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        TodoModel saveTodo = TodoModel.builder()
                .title(todo.getTitle())
                .completed(false)
                .completeOn(todo.getCompleteOn())
                .description(todo.getDescription())
                .user(user)
                .build();
        todoRepo.save(saveTodo);
        return "Todo saved successfully";
    }

    public List<?> getTodosByUserId(int id) {
        return List.of(mapper.convertValue(todoRepo.getTodosByUser(id), TodoRes[].class));
    }

    public String updateTodo(TodoUpdateReq model) throws Exception {
        TodoModel todo = todoRepo.findById(model.getId())
                .orElseThrow(() -> new RuntimeException("Could not find todo"));
        if (model.getTitle() != null) {
            todoRepo.updateTodoTitle(model.getTitle(), model.getId());
        } else if (model.getCompleteOn() != null) {
            todoRepo.updateTodoDate(model.getCompleteOn(), model.getId());
        } else if (model.getDescription() != null) {
            todoRepo.updateTodoDesc(model.getDescription(), model.getId());
        } else {
            todoRepo.updateTodoStatus(!todo.getCompleted(), model.getId());
        }
        return "Todo updated successfully";
    }

    public void deleteTodo(TodoUpdateReq todoReq) throws Exception {
        TodoModel todo = todoRepo.findById(todoReq.getId())
                .orElseThrow(() -> new RuntimeException("Could not find todo"));
        todoRepo.deleteTodoById(todoReq.getId());
    }
}
