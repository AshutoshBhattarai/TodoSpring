package com.application.todo.Todos;

import com.application.todo.Services.ResponseCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    private final ResponseCreator responseCreator;

    @GetMapping("/all")
    private List<TodoModel> getTodos() {
        return todoService.getTodos();
    }

    @PostMapping("/save")
    private ResponseEntity<?> saveTodo(@RequestBody TodoModel model) {
        try {
            TodoModel savedTodo = todoService.saveTodo(model);
            return responseCreator.successMessage(savedTodo);
        } catch (Exception e) {
            return responseCreator.errorMessage(e.getMessage());
        }
    }

    @PostMapping("/update")
    private ResponseEntity<?> updateTodo(@RequestBody TodoModel model) {
        try {
            String msg = todoService.updateTodo(model);
            return responseCreator.successMessage(msg);
        } catch (Exception e) {
            return responseCreator.errorMessage(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    private ResponseEntity<?> deleteTodo(@RequestBody TodoModel model) {
        try {
            todoService.deleteTodo(model);
            return responseCreator.successMessage("Todo deleted successfully");
        } catch (Exception e) {
            return responseCreator.errorMessage(e.getMessage());
        }
    }


    @GetMapping("/all/{id}")
    private List<Map<String, Object>> getTodosById(@PathVariable String id) {
        return todoService.getTodosById(Integer.parseInt(id));
    }
}
