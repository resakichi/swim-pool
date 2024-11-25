package com.swimpool.swim.pool.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swimpool.swim.pool.Entity.User;


public interface UserRepository extends JpaRepository<User, Long>{
    User findByLogin(String login);
}
