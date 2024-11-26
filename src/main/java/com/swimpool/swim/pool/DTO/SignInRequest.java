package com.swimpool.swim.pool.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Запрос на аутентификацию")
public class SignInRequest {
    
    @Schema(description = "Login user", example = "jack123")
    @Size(min = 5, max = 50, message = "Логин должен содержать от 5 до 50 символов")
    @NotBlank(message = "Логин не может быть пустым")
    private String login;

    @Schema(description = "Password user", example = "password")
    @Size(max = 255, message = "Длина пароля не должна превышать 255 симаолов")
    private String password;

    public SignInRequest() {
    }

    public SignInRequest(
            String login,
            String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
