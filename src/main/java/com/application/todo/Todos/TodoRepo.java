package com.application.todo.Todos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TodoRepo extends JpaRepository<TodoModel,Integer> {

    @Query(value = "Select * from todos where user_id = ?1",nativeQuery = true)
    public List<Map<String,Object>> getTodos(int id);
}
