package com.swimpool.swim.pool.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.swimpool.swim.pool.DTO.SignInRequest;
import com.swimpool.swim.pool.DTO.SignUpRequest;
import com.swimpool.swim.pool.Entity.User;
import com.swimpool.swim.pool.Entity.UserRole;

@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String signUp(SignUpRequest request){
        var encodedPassword = passwordEncoder.encode(request.getPassword());
        var user = new User(request.getLogin(), encodedPassword, UserRole.USER, 
                            request.getName(), request.getPhone(), request.getEmail());
        userService.create(user);

        return "{\"token : \"" + jwtService.generateToken(user) + "\"}";
    }

    public String signIn(SignInRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            request.getLogin(), request.getPassword()));

        var user = userService
        .userDetailsService()
        .loadUserByUsername(request.getLogin());

        var jwt = jwtService.generateToken(user);
        return "{\"token : \"" + jwtService.generateToken(user) + "\"}";
    }
}
