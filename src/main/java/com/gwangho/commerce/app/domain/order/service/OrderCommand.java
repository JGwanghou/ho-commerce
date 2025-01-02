package com.gwangho.commerce.app.domain.order.service;

import java.math.BigDecimal;
import java.util.List;

public class OrderCommand {
    public record CreateOrder(
            Long userId,
            List<CreateOrderItem> createOrderItems
    ) {
    }

    public record CreateOrderItem(
            Long productId,
            Long count,
            BigDecimal amount
    ) {
    }

}
