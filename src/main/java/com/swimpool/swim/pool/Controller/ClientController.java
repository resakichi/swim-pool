package com.swimpool.swim.pool.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swimpool.swim.pool.DTO.UpdateUserRequest;
import com.swimpool.swim.pool.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/client")
@Tag(name = "Работа с пользователем")
public class ClientController {

    private final UserService userService;
    
    public ClientController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Получение всех пользователя")
    @GetMapping("/all")
    public String getClients() {
        return userService.getAllUsers();
    }
    
    @Operation(summary = "Получение данных пользователя")
    @GetMapping("/get")
    public String getClient(@RequestParam Integer param) {
        return userService.getById(Long.valueOf(param));
    }

    @Operation(summary = "Обновление пользователя")
    @PostMapping("/update")
    public String updateUser(@RequestBody @Valid UpdateUserRequest request) {
        try {
            return userService.updateUser(request).toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
}
