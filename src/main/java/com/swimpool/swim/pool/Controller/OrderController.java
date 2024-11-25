package com.swimpool.swim.pool.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/timetable")
public class OrderController {
    
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
    
    
}
