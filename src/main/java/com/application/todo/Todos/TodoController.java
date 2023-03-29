package com.application.todo.Todos;

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

    @GetMapping("/all")
    private List<TodoModel> getTodos() {
        return todoService.getTodos();
    }

    @PostMapping("/save")
    private ResponseEntity<?> saveTodo(@RequestBody TodoModel model) {
        try {
            TodoModel savedTodo = todoService.saveTodo(model);
            return ResponseEntity.ok(savedTodo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all/{id}")
    private List<Map<String, Object>> getTodosById(@PathVariable String id) {
        return todoService.getTodosById(Integer.parseInt(id));
    }
}
