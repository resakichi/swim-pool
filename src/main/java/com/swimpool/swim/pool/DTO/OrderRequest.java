package com.swimpool.swim.pool.DTO;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;

@Schema(description = "Запрос на создание записи")
public class OrderRequest {
    
    @Schema(description = "Дата записи", example = "2024-11-11T19:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent(message = "Дата не может быть прошедшей")
    private LocalDateTime dateTime;

    @Schema(description = "Конечная дата для записи промежутка", example = "2024-11-11T20:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent(message = "Дата не может быть прошедшей")
    private LocalDateTime secondDateTime = null;

    public OrderRequest(LocalDateTime dateTime,
                LocalDateTime secondDateTime) {
        this.dateTime = dateTime;
        this.secondDateTime = secondDateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getSecondDateTime() {
        return secondDateTime;
    }

    public void setSecondDateTime(LocalDateTime secondDateTime) {
        this.secondDateTime = secondDateTime;
    }

    @Override
    public String toString() {
        return "OrderRequest [dateTime=" + dateTime + ", secondDateTime=" + secondDateTime + "]";
    }

}
