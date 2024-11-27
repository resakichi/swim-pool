package com.swimpool.swim.pool.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Обновление данных пользователя")
public class UpdateUserRequest {

    @Schema(description = "Name user", example = "Jack")
    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @Schema(description = "Email user", example = "jack@example.com")
    @NotBlank(message = "Почта не может быть пустой")
    @Email(message = "email адрес должен быть в формате user@example.com")
    private String email;

    @Schema(description = "Phone user", example = "+7999 555 66 33")
    @NotBlank(message = "Номер телефона не может быть пустым")
    private String phone;

    public UpdateUserRequest(
            @NotBlank(message = "Имя не может быть пустым") String name,
            @NotBlank(message = "Почта не может быть пустой") @Email(message = "email адрес должен быть в формате user@example.com") String email,
            @NotBlank(message = "Номер телефона не может быть пустым") String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
