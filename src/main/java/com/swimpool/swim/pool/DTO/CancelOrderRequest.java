package com.swimpool.swim.pool.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрос на создание записи")
public class CancelOrderRequest {
    @Schema(description = "Идентификатор записи", example = "1")
    private Integer orderId;

    public CancelOrderRequest(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
