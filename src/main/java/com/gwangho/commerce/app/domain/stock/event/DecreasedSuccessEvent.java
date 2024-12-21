package com.gwangho.commerce.app.domain.stock.event;

import com.gwangho.commerce.app.domain.order.Order;
import com.gwangho.commerce.app.domain.order.OrderItem;
import com.gwangho.commerce.app.domain.product.Product;
import com.gwangho.commerce.app.domain.stock.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class DecreasedSuccessEvent {
    private Order order;
    private List<OrderItem> orderItems;

    public DecreasedSuccessEvent(Order order, List<OrderItem> orderItems) {
        this.order = order;
        this.orderItems = orderItems;
    }
}
