package com.application.todo.Todos;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface TodoRepo extends JpaRepository<TodoModel, Integer> {

    @Query(value = "Select * from todos where user_id = ?1", nativeQuery = true)
    public List<Map<String, Object>> getTodos(int id);

    @Transactional
    @Modifying
    @Query("UPDATE TodoModel t SET t.completed = :status where t.id = :todoId")
    public void updateTodoStatus(@Param("status") Boolean status, @Param("todoId") int todoId);

    @Transactional
    @Modifying
    @Query("UPDATE TodoModel t SET t.title = :title where t.id = :todoId")
    public void updateTodoTitle(@Param("title") String title, @Param("todoId") int todoId);

    @Transactional
    @Modifying
    @Query("UPDATE TodoModel t SET t.completeOn = :date where t.id = :todoId")
    public void updateTodoDate(@Param("date") LocalDate date, @Param("todoId") int todoId);

    @Transactional
    @Modifying
    @Query("UPDATE TodoModel t SET t.description = :desc where t.id = :todoId")
    public void updateTodoDesc(@Param("desc") String desc, @Param("todoId") int todoId);
}
