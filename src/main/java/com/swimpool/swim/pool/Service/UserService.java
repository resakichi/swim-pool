package com.swimpool.swim.pool.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.swimpool.swim.pool.Entity.User;
import com.swimpool.swim.pool.Repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User save(User user){
        return repository.save(user);
    }

    public User create(User user){
        if (repository.existsByLogin(user.getUsername())){
            throw new RuntimeException("Пользователь с таким логином существует");
        }
        
        if (repository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Пользователь с таким email существует");
        }
        
        if (repository.existsByPhone(user.getPhone())){
            throw new RuntimeException("Пользователь с таким телефоном существует");
        }

        return save(user);
    }

    public User getByLogin(String login){
        return repository.findByLogin(login)
        .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByLogin;
    }

    public User getCurrentUser(){
        var login = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByLogin(login);
    }
}
