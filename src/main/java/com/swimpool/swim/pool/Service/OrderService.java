package com.swimpool.swim.pool.Service;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.swimpool.swim.pool.Entity.Order;
import com.swimpool.swim.pool.Repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order save(Order order){
        return repository.save(order);
    }

    /* public Order create(Order order){
        if (checkOrdersOnDate(order.getDate())){
            
        }
    } */

    /* public boolean checkOrdersOnDate(Timestamp date){
        if (repository.countOrdersOnDate(date) < 10){
            return true;
        }
        return false;
    } */
}
