package com.gwangho.commerce.app.domain.stock.event;

import com.gwangho.commerce.app.domain.order.Order;
import com.gwangho.commerce.app.domain.order.OrderItem;
import lombok.Getter;

import java.util.List;

@Getter
public class DecreasedFailedEvent {
    private Order order;
    private List<OrderItem> orderItems;

    public DecreasedFailedEvent(Order order, List<OrderItem> orderItems) {
        this.order = order;
        this.orderItems = orderItems;
    }
}
