package com.swimpool.swim.pool.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.swimpool.swim.pool.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    /* @Query("SELECT COUNT(*) FROM orders WHERE date BETWEEN :openingHour AND :closingHour")
    Optional<Integer> countOrdersOnDate(@Param("openingHour") LocalDateTime openingHour, 
    @Param("closingHour") LocalDateTime closingHour); */
}
