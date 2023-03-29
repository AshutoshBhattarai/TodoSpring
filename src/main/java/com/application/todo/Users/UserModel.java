package com.application.todo.Users;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue
    int id;
    String name;
    String password;
    @Column(unique = true)
    String email;

}
