package com.swimpool.swim.pool.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swimpool.swim.pool.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
