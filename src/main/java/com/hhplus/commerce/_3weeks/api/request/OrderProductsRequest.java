package com.hhplus.commerce._3weeks.api.request;

import lombok.Getter;

@Getter
public class OrderProductsRequest {
    private Long product_id;
    private Long buy_count;

    public OrderProductsRequest(Long product_id, Long buy_count) {
        if (buy_count < 0 || buy_count == 0) {
            throw new IllegalStateException("상품은 1개 이상 구매 가능합니다.");
        }

        this.product_id = product_id;
        this.buy_count = buy_count;
    }
}
