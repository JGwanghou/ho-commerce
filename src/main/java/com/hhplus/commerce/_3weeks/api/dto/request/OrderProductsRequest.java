package com.hhplus.commerce._3weeks.api.dto.request;

import lombok.Getter;

@Getter
public class OrderProductsRequest {
    private Long product_id;
    private int product_quantity;

    public OrderProductsRequest(Long product_id, int buy_count) {
        this.product_id = product_id;
        this.product_quantity = buy_count;
    }
}
