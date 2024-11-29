package com.swimpool.swim.pool.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swimpool.swim.pool.DTO.CancelOrderRequest;
import com.swimpool.swim.pool.DTO.OrderRequest;
import com.swimpool.swim.pool.Service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/timetable")
@Tag(name = "Работа с записями")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Получение всех записей на дату",
    parameters = {
        @Parameter(name = "date",
                    description = "Дата записей",
                    schema = @Schema(type = "LocalDate"), example = "2024-11-29")
    })
    @GetMapping("/all")
    public String getAll(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return orderService.allByDate(date);
    }
    
    @Operation(summary = "Получение списка свободного времени на дату",
    parameters = {
        @Parameter(name = "date",
                    description = "Дата свободных записей",
                    schema = @Schema(type = "LocalDate"), example = "2024-11-29")
    })
    @GetMapping("/available")
    public String getAvailable(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return orderService.available(date);
    }

    @Operation(summary = "Поиск по имени и дате",
    parameters = {
        @Parameter(name = "name", description = "Имя пользователя", example = "Jack"),
        @Parameter(name = "date",
                    description = "Дата формата yyyy-MM-ddTHH:mm:ss",
                    schema = @Schema(type = "LocalDate"), example = "2024-11-29T19:00:00")
    })
    @GetMapping("/find")
    public String findByNameDate(@RequestParam("name") String name,
    @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return orderService.findByNameDate(name, date).toString();
    }
    
    @Operation(summary = "Бронирование времени",
    parameters = {
        @Parameter(name = "dateTime", description = "Начальная дата для записи",
        schema = @Schema(type = "LocalDate"), example = "2024-11-29T13:00:00"),
        @Parameter(name = "secondDateTime",
                    description = "Конечная дата для записи промежутка",
                    schema = @Schema(type = "LocalDate"), example = "2024-11-29T18:00:00")
    })
    @PostMapping("/reserve")
    public String reserve(@RequestBody @Valid OrderRequest request) {
        try {
            return orderService.reserve(request);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Operation(summary = "Отмена записи",
    parameters = {
        @Parameter(name = "order id", description = "Id записи", 
        schema = @Schema(type = "Integer"), example = "1")
    })
    @DeleteMapping("/cancel")
    public void cancel(@RequestBody CancelOrderRequest request) {
        orderService.cancelOrder(request);
    }

}
