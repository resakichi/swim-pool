package com.swimpool.swim.pool.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swimpool.swim.pool.DTO.OrderRequest;
import com.swimpool.swim.pool.Repository.OrderEntityRepository;
import com.swimpool.swim.pool.Service.OrderService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/timetable")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderEntityRepository repository;

    @GetMapping("/all")
    public String getAll(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping("/available")
    public String getAvailable(@RequestParam String param) {
        return new String();
    }
    
    @PostMapping("/reserve")
    public String reserve(@RequestBody @Valid OrderRequest request) {
        return orderService.reserve(request).toString();
    }

    @GetMapping("/cancel")
    public String cancel(@RequestParam String param) {
        return new String();
    }

    
}
