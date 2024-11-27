package com.swimpool.swim.pool.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swimpool.swim.pool.DTO.JwtAuthenticationResponse;
import com.swimpool.swim.pool.DTO.SignInRequest;
import com.swimpool.swim.pool.DTO.SignUpRequest;
import com.swimpool.swim.pool.DTO.UpdateUserRequest;
import com.swimpool.swim.pool.Service.AuthenticationService;
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

    private final AuthenticationService authenticationService;
    private final UserService userService;
    
    public ClientController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @Operation(summary = "Получение всех пользователя")
    @GetMapping("/all")
    public String getClients() {
        return userService.getAllUsers();
    }
    
    @Operation(summary = "Получение данных пользователя")
    @GetMapping("/get")
    public String getClient(@RequestParam String param) {
        return userService.getById(Long.valueOf(param));
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/add")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @Operation(summary = "Обновление пользователя")
    @PostMapping("/update")
    public String updateUser(@RequestBody @Valid UpdateUserRequest request) {
        return userService.updateUser(request).toString();
    }
    
}
