package ru.praktikum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public String name;
    public String email;
    public String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
