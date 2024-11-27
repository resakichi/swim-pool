package com.swimpool.swim.pool;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.swimpool.swim.pool.DTO.SignUpRequest;
import com.swimpool.swim.pool.Entity.User;
import com.swimpool.swim.pool.Entity.UserRole;
import com.swimpool.swim.pool.Service.AuthenticationService;
import com.swimpool.swim.pool.Service.JwtService;
import com.swimpool.swim.pool.Service.UserService;

import io.jsonwebtoken.lang.Objects;

public class JwtTest {
    //private final AuthenticationService authenticationService;
    private final JwtService jwtService = new JwtService();


    @Test
    void createToken(){
        var request = new SignUpRequest("test", "test@example.com", 
        "phone", "jackson", "password");
        var user = new User(request.getLogin(), request.getPassword(), UserRole.USER, 
                            request.getName(), request.getPhone(), request.getEmail());
        var token = jwtService.generateToken(user);
        System.out.println(token);
        assertTrue(!Objects.isEmpty(token));
    }
}
