package com.application.todo.Todos;

import com.application.todo.JWT.JwtService;
import com.application.todo.Services.ResponseCreator;
import com.application.todo.Todos.RequestHandlers.TodoSaveReq;
import com.application.todo.Todos.RequestHandlers.TodoUpdateReq;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
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
    private final JwtService jwtService;
    private final ResponseCreator responseCreator;

    @GetMapping("/get/all")
    private List<TodoModel> getTodos() {
        return todoService.getTodos();
    }

    @PostMapping("/save")
    private ResponseEntity<?> saveTodo(@RequestBody TodoSaveReq model, HttpServletRequest request) {
        try {
            String savedTodo = todoService.saveTodo(model, getIdFromToken(request));
            return responseCreator.successMessage(savedTodo);
        } catch (Exception e) {
            return responseCreator.errorMessage(e.getMessage());
        }
    }

    @PostMapping("/update")
    private ResponseEntity<?> updateTodo(@RequestBody TodoUpdateReq model) {
        try {
            String msg = todoService.updateTodo(model);
            return responseCreator.successMessage(msg);
        } catch (Exception e) {
            return responseCreator.errorMessage(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    private ResponseEntity<?> deleteTodo(@RequestBody TodoUpdateReq model) {
        try {
            todoService.deleteTodo(model);
            return responseCreator.successMessage("Todo deleted successfully");
        } catch (Exception e) {
            return responseCreator.errorMessage(e.getMessage());
        }
    }


    //    @GetMapping("/all/{id}")
//    private List<Map<String, Object>> getTodosById(@PathVariable String id, HttpServletRequest request) {
//        return todoService.getTodosByUserId(Integer.parseInt(id));
//    }
    @GetMapping("/user/all")
    private List<?> getUserTodos(HttpServletRequest request) {

        try {
            return todoService.getTodosByUserId(getIdFromToken(request));
        } catch (Exception e) {
            return List.of(responseCreator.errorMessage(e.getMessage()));
        }
    }

    private int getIdFromToken(HttpServletRequest request) throws Exception {
        String getToken = request.getHeader("Authorization").substring(7);
        Claims claims = jwtService.getAllClaims(getToken);
        Object user_id = claims.get("Id");
        return Integer.parseInt(user_id.toString());
    }

}
