package com.swimpool.swim.pool.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swimpool.swim.pool.Entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    @SuppressWarnings("null")
    List<User> findAll();
}
