package com.swimpool.swim.pool.DTO;

import java.util.regex.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Запрос на регистрацию")
public class SignUpRequest {

    @Schema(description = "Login user", example = "jack123")
    @Size(min = 5, max = 50, message = "Логин должен содержать от5 до 50 символов")
    @NotBlank(message = "Логин не может быть пустым")
    private String login;

    @Schema(description = "Email user", example = "jack@mail.com")
    @NotBlank(message = "Адрес почты не может быть пустым")
    @Email(message = "email адрес должен быть в формате user@example.com")
    private String email;

    @Schema(description = "Phone user", example = "+7999 555 66 33")
    @NotBlank(message = "Номер телефона не может быть пустым")
    private String phone;

    @Schema(description = "Name user", example = "Jack")
    @NotBlank(message = "Имя не может быть пустым")
    private String name;
    
    @Schema(description = "Password user", example = "password")
    @Size(max = 255, message = "Длина пароля не должна превышать 255 симаолов")
    private String password;

    @Transient
    private final Pattern VALID_PHONE_NUMBER_REGEX = 
    Pattern.compile("^\\+?\\d{10,15}$");

    public SignUpRequest() {
    }

    public SignUpRequest(
            @Size(min = 5, max = 50, message = "Логин должен содержать от 5 до 50 символов") @NotBlank(message = "Логин не может быть пустым") String login,
            @NotBlank(message = "Адрес почты не может быть пустым") @Email(message = "email адрес должен быть в формате user@example.com") String email,
            @NotBlank(message = "Номер телефона не может быть пустым") String phone, String name,
            @Size(max = 255, message = "Длина пароля не должна превышать 255 симаолов") String password) {
        this.login = login;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.password = password;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public boolean setPhone(String phone) {
        var matcher = VALID_PHONE_NUMBER_REGEX.matcher(phone);
        if (matcher.matches()) {
            this.phone = phone;
            return true;
        }
        return false;
    }
    /* public void setPhone(String phone){
        this.phone = phone;
    } */

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignUpRequest [login=" + login + ", email=" + email + ", phone=" + phone + ", name=" + name
                + ", password=" + password + "]";
    }    
}
