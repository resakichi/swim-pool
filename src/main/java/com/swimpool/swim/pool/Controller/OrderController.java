package com.swimpool.swim.pool.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swimpool.swim.pool.Repository.OrderRepository;
import com.swimpool.swim.pool.Service.OrderService;

import java.sql.Timestamp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/timetable")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public String getAll(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping("/available")
    public String getAvailable(@RequestParam String param) {
        return new String();
    }
    
    @PostMapping("/reserve")
    public String reserve(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }

    @GetMapping("/cancel")
    public String cancel(@RequestParam String param) {
        return new String();
    }
    
    /* @GetMapping("/test")
    public int getMethodName() {
        return orderService.countOrdersOnDate(new Timestamp(System.currentTimeMillis()));
    } */
    
}
