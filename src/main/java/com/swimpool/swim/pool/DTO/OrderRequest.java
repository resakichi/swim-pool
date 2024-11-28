package com.swimpool.swim.pool.DTO;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;

@Schema(description = "Запрос на создание записи")
public class OrderRequest {
    
    @Schema(description = "Дата записи", example = "2024-11-11T20:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Future
    private LocalDateTime dateTime;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public OrderRequest(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "OrderRequest [dateTime=" + dateTime + "]";
    }

}
