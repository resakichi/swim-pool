package com.swimpool.swim.pool.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swimpool.swim.pool.DTO.OrderRequest;
import com.swimpool.swim.pool.Service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/timetable")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Получение всех записей на дату")
    @GetMapping("/all")
    public String getAll(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate param) {
        return orderService.allByDate(param);
    }
    
    @Operation(summary = "Получение списка свободного времени на дату")
    @GetMapping("/available")
    public String getAvailable(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate param) {
        return orderService.available(param);
    }

    @Operation(summary = "Поиск по имени и дате")
    @GetMapping("/find")
    public String findByNameDate(@RequestParam("name") String name,
    @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return orderService.findByNameDate(name, date).toString();
    }
    
    @Operation(summary = "Бронирование времени")
    @PostMapping("/reserve")
    public String reserve(@RequestBody @Valid OrderRequest request) {
        try {
            return orderService.reserve(request);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Operation(summary = "Отмена записи")
    @DeleteMapping("/cancel")
    public void cancel(@RequestBody Integer request) {
        orderService.cancelOrder((long) request);
    }

}
