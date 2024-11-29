package com.swimpool.swim.pool.Service;

import java.nio.Buffer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Stream;

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

    //Добавление записей по диапазону дат
    private String createOrders(LocalDateTime start, LocalDateTime end){
        StringBuilder builder = new StringBuilder("{");
        Stream.iterate(start, date -> date.plusHours(1))
            .limit(ChronoUnit.HOURS.between(start, end) + 1)
            .forEach(date -> {
                var order = new Order();
                order.setDate(date);
                order.setUser(userService.getCurrentUser());
                repository.save(order);
                builder.append("["+ order.toString() +"],");
            });
            builder.deleteCharAt(builder.length() - 1);
            builder.append("}");
        return builder.toString();
    }

    //Создание новой записи
    public String reserve(OrderRequest request) throws Exception{
        var date = request.getDateTime().toLocalTime();
        if(checkWorkingHours(date)){

            //Проверка на запись клиента в этот день
            if(repository.countUsersOnDate(
                request.getDateTime().toLocalDate(), 
                userService.getCurrentUser().getId()) == 0){
                
                var startTime = request.getDateTime();
                var endTime = request.getSecondDateTime();

                //Проверка является ли дата периодом
                if (endTime != null &&
                startTime.isBefore(endTime) &&
                !startTime.isEqual(endTime) &&
                checkWorkingHours(endTime.toLocalTime())){

                    //Проверка на совпадение даты
                    if (startTime.toLocalDate().isEqual(endTime.toLocalDate())){
                        return createOrders(startTime, endTime);
                    }else{
                        throw new Exception("Конечная дата не совпадает с днем начала");
                    }
                }else{
                    //Проверка свободно ли время
                    if (repository.countOrdersOnTime(request.getDateTime()) < 4){
                        var order = new Order();
                        order.setDate(request.getDateTime());
                        order.setUser(userService.getCurrentUser());
                        repository.save(order);
                        return "{" + order.toString() + "}";
                    }else{
                        throw new Exception("Ограничение на количество"
                        + "записей в час, выберите другое время");
                    }
                }
            }else{
                throw new Exception("Пользователь уже записан на эту дату");
            }
        }else{
            throw new Exception("Дата не входит в часы работы");
        }
    }

    //Проверка на часы работы 
    private boolean checkWorkingHours(LocalTime date){
        if(!date.isBefore(openingHour) && !date.isAfter(closingHour)){
            return true;
        }
        return false;
    }

    //Получение свободных записей
    public String available(LocalDate date){
        var listAvailabe = repository.countEntitiesPerHour(date);
        StringBuilder strBuilder = new StringBuilder("{");
        for (var element : listAvailabe.entrySet()) {
            if (element.getValue() < 4){
                strBuilder.append("[\"time\" : \"" + element.getKey() + "\","
                + "\"count\" : " + element.getValue() + "],");
            }
        }
        strBuilder.deleteCharAt(strBuilder.length()-1);
        strBuilder.append("}");
        return strBuilder.toString();
    }

    public String allByDate(LocalDate date){
        var listAvailabe = repository.countEntitiesPerHour(date);
        StringBuilder strBuilder = new StringBuilder("{");
        for (var element : listAvailabe.entrySet()) {
            strBuilder.append("[\"time\" : \"" + element.getKey() + "\","
            + "\"count\" : " + element.getValue() + "],"); 
        }
        strBuilder.deleteCharAt(strBuilder.length()-1);
        strBuilder.append("}");
        return strBuilder.toString();
    }

    //Отмена записи
    public void cancelOrder(Long id){
        repository.removeById(id);
    }

    public Order findByNameDate(String name, LocalDateTime date){
        return repository.findByDateName(date, name);
    }
}
