package com.swimpool.swim.pool.Controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swimpool.swim.pool.Service.UserService;


@RestController
@RequestMapping("/example")
@Tag(name = "Примеры", description = "Примеры запросов с разными правами доступа")
public class ExampleController {
    @Autowired
    private UserService service;

    @GetMapping
    @Operation(summary = "Доступен только авторизованным пользователям")
    public String example() {
        return service.getCurrentUser().toString();
    }

    @GetMapping("/admin")
    @Operation(summary = "Доступен только авторизованным пользователям с ролью ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public String exampleAdmin() {
        return "Hello, admin!";
    }

}