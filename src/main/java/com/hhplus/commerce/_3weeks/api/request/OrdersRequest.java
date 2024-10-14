package com.hhplus.commerce._3weeks.api.request;

import lombok.Getter;

import java.util.List;

@Getter
public class OrdersRequest {
    private Long id;
    private Long user_id;
    private List<OrderProductsRequest> products;

    public OrdersRequest(Long id, Long user_id, List<OrderProductsRequest> products) {
        this.id = id;
        this.user_id = user_id;
        this.products = products;
    }
}
