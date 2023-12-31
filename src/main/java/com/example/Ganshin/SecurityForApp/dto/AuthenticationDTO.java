package com.example.Ganshin.SecurityForApp.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AuthenticationDTO {

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 70, message = "Имя должно иметь количество символов от 2 до 70")
    private String username;
    @NotNull
    @NotEmpty
    @Size(min=3, max = 20, message = "Пароль должен содержать от 3 до 20 символов")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
