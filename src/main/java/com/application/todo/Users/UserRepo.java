package com.application.todo.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserModel, Integer> {

    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    Optional<UserModel> findUserByEmail(String email);
}
