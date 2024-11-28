package com.swimpool.swim.pool.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.swimpool.swim.pool.Entity.Order;
import com.swimpool.swim.pool.Repository.OrderRepository;

@Service
public class OrderService {
    private final OrderRepository repository;
    private static final LocalTime openingHour = LocalTime.of(10, 0);
    private static final LocalTime closingHour = LocalTime.of(20, 0);

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order save(Order order){
        return repository.save(order);
    }

    /* public Order reserve(Order order){
        var date = order.getDate().toLocalDateTime().toLocalTime();
        if(!date.isBefore(openingHour) && !date.isAfter(closingHour)){
            if (checkOrdersOnDate(order.getDate())){
            
            }
        }
        
    } */

    /* public boolean checkOrdersOnDate(Timestamp date){
        if (repository.countOrdersOnDate(date) < 10){
            return true;
        }
        return false;
    }  */

    /* public Integer countOrdersOnDate(Timestamp date){
        var openingHour = date.toLocalDateTime().with(LocalTime.MIN);
        openingHour.plus(10, ChronoUnit.HOURS);

        var closingHour = date.toLocalDateTime().with(LocalTime.MIN);
        closingHour.plus(20, ChronoUnit.HOURS);
        return repository.countOrdersOnDate(openingHour, closingHour).get();
    } */
}
