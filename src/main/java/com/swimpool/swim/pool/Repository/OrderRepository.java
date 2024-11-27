package com.swimpool.swim.pool.Repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.swimpool.swim.pool.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    /* @Query("SELECT COUNT(*) FROM orders WHERE order_date = ?1")
    int countOrdersOnDate(Timestamp date); */
}
