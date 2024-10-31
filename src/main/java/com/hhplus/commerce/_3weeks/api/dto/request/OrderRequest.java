package com.hhplus.commerce._3weeks.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class OrderRequest {
    private Long user_id;
    private List<OrderProductsRequest> products;

    @Setter
    private Long paymentPrice;

    public OrderRequest(Long user_id, List<OrderProductsRequest> products, Long paymentPrice) {
        this.user_id = user_id;
        this.products = products;
        this.paymentPrice = paymentPrice;
    }

}
