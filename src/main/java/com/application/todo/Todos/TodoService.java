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

    public TodoModel saveTodo(TodoModel todo, int id) throws RuntimeException {
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
        return todoRepo.save(saveTodo);
    }

    public List<Map<String, Object>> getTodosByUserId(int id) {
        return todoRepo.getTodosByUser(id);
    }

    public String updateTodo(TodoModel model) throws Exception {
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

    public void deleteTodo(TodoModel todoReq) throws Exception {
        TodoModel todo = todoRepo.findById(todoReq.getId())
                .orElseThrow(() -> new RuntimeException("Could not find todo"));
        todoRepo.delete(todoReq);
    }
}
