package com.swimpool.swim.pool.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swimpool.swim.pool.Entity.Client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/client")
public class ClientController {
    
    @GetMapping("/all")
    public String getClients(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping("/get")
    public String getClient(@RequestParam String param) {
        return new String();
    }
    
    @PostMapping("/add")
    public String addClient(@RequestBody Client client) {
        //TODO: process POST request
        
        return "entity";
    }
    
    @PostMapping("/update")
    public String updateClient(@RequestBody Client client) {
        //TODO: process POST request
        
        return "entity";
    }
    
}
