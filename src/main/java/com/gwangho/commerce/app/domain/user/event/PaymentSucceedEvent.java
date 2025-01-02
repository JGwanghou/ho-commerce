package com.gwangho.commerce.app.domain.user.event;

import com.gwangho.commerce.app.domain.order.Order;
import lombok.Getter;

@Getter
public class PaymentSucceedEvent {
    private Long userId;
    private Order order;

    public PaymentSucceedEvent(Long userId, Order order) {
        this.userId = userId;
        this.order = order;
    }
}
