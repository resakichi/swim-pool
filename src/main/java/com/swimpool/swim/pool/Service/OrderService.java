package com.swimpool.swim.pool.Service;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swimpool.swim.pool.DTO.OrderRequest;
import com.swimpool.swim.pool.Entity.Order;
import com.swimpool.swim.pool.Repository.OrderEntityRepository;

@Service
public class OrderService {
    @Autowired
    private OrderEntityRepository repository;

    @Autowired
    private UserService userService;
    private static final LocalTime openingHour = LocalTime.of(10, 0);
    private static final LocalTime closingHour = LocalTime.of(20, 0);


    public Order reserve(OrderRequest request){
        var date = request.getDateTime().toLocalTime();
        if(!date.isBefore(openingHour) && !date.isAfter(closingHour)){
            if (repository.countOrdersOnTime(request.getDateTime()) < 10){
                var order = new Order();
                order.setDate(request.getDateTime());
                order.setUser(userService.getCurrentUser());
                repository.save(order);
                return order;
            }else{
                System.out.println("ЗАНЯТО");
            }
        }else{
            System.out.println("NOT IN TIME");
            //TODO Сделать исключения 
        }
        return null;
    }


    /* public Integer countOrdersOnDate(Timestamp date){
        var openingHour = date.toLocalDateTime().with(LocalTime.MIN);
        openingHour = openingHour.plus(10, ChronoUnit.HOURS);

        var closingHour = date.toLocalDateTime().with(LocalTime.MIN);
        closingHour = closingHour.plus(20, ChronoUnit.HOURS);
        return repository.countOrdersOnDate(openingHour, closingHour).get();
    } */
}
